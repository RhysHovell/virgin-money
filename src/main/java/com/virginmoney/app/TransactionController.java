package com.virginmoney.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{category}")
    private List<TransactionVO> findTransactionsByCategory(@PathVariable final String category){
        return transactionService.findTransactionsByCategory(category);
    }

    @GetMapping("/{category}/totalOutgoing")
    private BigDecimal findTotalOutgoingByCategory(@PathVariable final String category){
        return transactionService.findTotalOutgoingByCategory(category);
    }

    @GetMapping("/{category}/{year}/minAmount")
    private BigDecimal findLowestSpendForYearByCategory(@PathVariable final String category,@PathVariable final Integer year){
        return transactionService.findLowestSpendForYearByCategory(category, year);
    }

    @GetMapping("/{category}/{year}/maxAmount")
    private BigDecimal findHighestSpendForYearByCategory(@PathVariable final String category, @PathVariable final Integer year){
        return transactionService.findHighestSpendForYearByCategory(category, year);
    }

    @GetMapping("/{category}/averageMonthlySpend")
    private BigDecimal findAverageMonthlySpendByCategory(@PathVariable final String category){
        return transactionService.findAverageMonthlySpendByCategory(category);
    }

}
