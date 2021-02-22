package com.virginmoney.app;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface TransactionService {
    List<TransactionVO> findTransactionsByCategory(String category);

    BigDecimal findTotalOutgoingByCategory(String category);

    BigDecimal findHighestSpendForYearByCategory(String category, Integer year);

    BigDecimal findLowestSpendForYearByCategory(String category, Integer year);

    BigDecimal findAverageMonthlySpendByCategory(String category);
}
