package com.virginmoney.app;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionQueryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService service;

    @Test
    @DisplayName("Get transactions by category")
    public void testFindTransactionsByCategory() throws Exception {
        final String category = "Groceries";
        TransactionVO transaction1 = createTransaction(LocalDate.now(), BigDecimal.valueOf(10.40), category, "card","Tesco");
        TransactionVO transaction2 = createTransaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(12.40), category, "card","Tesco");
        when(service.findTransactionsByCategory(category)).thenReturn(Arrays.asList(transaction1, transaction2));

        mockMvc.perform(get("/Groceries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Get total outgoing amount by category")
    public void testTotalOutgoingByCategory() throws Exception {
        final String category = "Groceries";
        TransactionVO transaction1 = createTransaction(LocalDate.now(), BigDecimal.valueOf(10.40), category, "card","Tesco");
        TransactionVO transaction2 = createTransaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(12.40), category, "card","Tesco");
        final BigDecimal totalOutgoing = transaction1.getAmount().add(transaction2.getAmount());
        when(service.findTotalOutgoingByCategory(category)).thenReturn(totalOutgoing);

        mockMvc.perform(get("/Groceries/totalOutgoing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(22.80)));
    }

    @Test
    @DisplayName("Get highest spend amount by category for a given year")
    public void testFindHighestSpendByCategoryForYear() throws Exception {
        final String category = "Groceries";
        TransactionVO transaction1 = createTransaction(LocalDate.now(), BigDecimal.valueOf(10.40), category, "card","Tesco");
        TransactionVO transaction2 = createTransaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(12.40), category, "card","Tesco");
        when(service.findHighestSpendForYearByCategory(category,LocalDate.now().getYear())).thenReturn(transaction2.getAmount());

        mockMvc.perform(get("/Groceries/2021/maxAmount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(12.40)));
    }

    @Test
    @DisplayName("Get highest spend amount by category for a given year")
    public void testFindLowestSpendByCategoryForYear() throws Exception {
        final String category = "Groceries";
        TransactionVO transaction1 = createTransaction(LocalDate.now(), BigDecimal.valueOf(10.40), category, "card","Tesco");
        TransactionVO transaction2 = createTransaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(12.40), category, "card","Tesco");
        when(service.findLowestSpendForYearByCategory(category, 2021)).thenReturn(transaction1.getAmount());

        mockMvc.perform(get("/Groceries/2021/minAmount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(10.40)));
    }

    @Test
    @DisplayName("Get average monthly spend amount for a category")
    public void testAverageMonthlySpendByCategory() throws Exception {
        final String category = "Groceries";
        TransactionVO transaction1 = createTransaction(LocalDate.now(), BigDecimal.valueOf(10.40), category, "card","Tesco");
        TransactionVO transaction2 = createTransaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(12.40), category, "card","Tesco");
        BigDecimal average = transaction1.getAmount().add(transaction2.getAmount());
        average = average.divide(BigDecimal.valueOf(2));
        when(service.findAverageMonthlySpendByCategory(category)).thenReturn(average);

        mockMvc.perform(get("/Groceries/averageMonthlySpend"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(11.4)));
    }


    private TransactionVO createTransaction(final LocalDate transactionDate, final BigDecimal amount, final String category, final String type, final String vendor) {
        final TransactionVO transaction = new TransactionVO();
        transaction.setTransactionDate(transactionDate);
        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setType(type);
        transaction.setVendor(vendor);
        return transaction;
    }

}
