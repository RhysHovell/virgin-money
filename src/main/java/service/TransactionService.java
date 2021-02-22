package service;

import entity.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface TransactionService {
    List<Transaction> findTransactionsByCategory(String category);

    BigDecimal findTotalOutgoingByCategory(String category);

    BigDecimal findHighestSpendForYearByCategory(String category, Integer year);

    BigDecimal findLowestSpendForYearByCategory(String category, Integer year);

    BigDecimal findAverageMonthlySpendByCategory(String category);
}
