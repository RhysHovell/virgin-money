package com.virginmoney.app.controller;

import com.virginmoney.app.service.TransactionService;
import com.virginmoney.app.data.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private List<TransactionVO> findTransactionsByCategory(@PathVariable final String category){
        return transactionService.findTransactionsByCategory(category);
    }

    @GetMapping("/{category}/totalOutgoing")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findTotalOutgoingByCategory(@PathVariable final String category){
        return transactionService.findTotalOutgoingByCategory(category);
    }

    @GetMapping("/{category}/maxAmount")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findHighestSpendForYearByCategory(@PathVariable final String category,@PathVariable final Integer year){
        return transactionService.findHighestSpendForYearByCategory(category, year);
    }

    @GetMapping("/{category}/averageMonthlySpend")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findAverageMonthlySpendByCategory(@PathVariable final String category){
        return transactionService.findAverageMonthlySpendByCategory(category);
    }

    @GetMapping("/{category}/minAmount")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findLowestSpendForYearByCategory(@PathVariable final String category,@PathVariable final Integer year){
        return transactionService.findLowestSpendForYearByCategory(category, year);
    }
}
