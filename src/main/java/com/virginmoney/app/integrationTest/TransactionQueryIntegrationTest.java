package com.virginmoney.app.integrationTest;

import com.virginmoney.app.controller.TransactionController;
import com.virginmoney.app.data.TransactionRepository;
import com.virginmoney.app.entity.Transaction;
import com.virginmoney.app.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TransactionQueryIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionService service;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(transactionController).build();
        mvc = MockMvcBuilders.standaloneSetup(service).build();
    }

    @Test
    @DisplayName("Get transactions by category")
    public void testFindTransactionsByCategory() throws Exception {
        final String category = "/Groceries";
        createTransaction(LocalDate.now(), BigDecimal.valueOf(10.40),category, "card","Tesco");
        createTransaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(12.40), category, "card","Tesco");

        RequestBuilder request = MockMvcRequestBuilders.get("/category" + category);

        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get total outgoing transaction amount by category")
    public void testFindTotalOutgoingByCategory() throws Exception {
        final String category = "/Groceries";
        createTransaction(LocalDate.now(), BigDecimal.valueOf(10.40),category, "card","Tesco");
        createTransaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(12.40), category, "card","Tesco");

        RequestBuilder request = MockMvcRequestBuilders.get("category" + category + "totalOutgoing");

        mvc.perform(request)
                .andExpect(status().isOk());
    }


    private void createTransaction(final LocalDate transactionDate, final BigDecimal amount, final String category, final String type, final String vendor) {
        final Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDate);
        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setType(type);
        transaction.setVendor(vendor);
        saveTransaction(transaction);
    }

    private void saveTransaction(final Transaction transaction) {
        transactionRepository.saveAndFlush(transaction);
    }

}
