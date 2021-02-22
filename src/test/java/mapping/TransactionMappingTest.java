package mapping;

import data.TransactionVO;
import entity.Transaction;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionMappingTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertTransactionEntityToTransactionVO_thenCorrect() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setAmount(BigDecimal.valueOf(10.40));
        transaction.setCategory("Groceries");
        transaction.setType("card");
        transaction.setVendor("Tesco");

        TransactionVO transactionVO = modelMapper.map(transaction, TransactionVO.class);
        assertEquals(transaction.getId(), transactionVO.getId());
        assertEquals(transaction.getTransactionDate(), transactionVO.getTransactionDate());
        assertEquals(transaction.getAmount(), transactionVO.getAmount());
        assertEquals(transaction.getCategory(), transactionVO.getCategory());
        assertEquals(transaction.getType(), transactionVO.getType());
        assertEquals(transaction.getVendor(), transactionVO.getVendor());
    }
}
