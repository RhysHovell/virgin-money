package controller;

import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@RestController("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private List<Transaction> findTransactionsByCategory(final String category){
        return transactionService.findTransactionsByCategory(category);
    }

    @GetMapping("/category/{category}/totalOutgoing")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findTotalOutgoingByCategory(final String category){
        return transactionService.findTotalOutgoingByCategory(category);
    }

    @GetMapping("/category/{category}/maxAmount")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findHighestSpendForYearByCategory(final String category, final Integer year){
        return transactionService.findHighestSpendForYearByCategory(category, year);
    }

    @GetMapping("/category/{category}/averageMonthlySpend")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findAverageMonthlySpendByCategory(final String category){
        return transactionService.findAverageMonthlySpendByCategory(category);
    }

    @GetMapping("/category/{category}/minAmount")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private BigDecimal findLowestSpendForYearByCategory(final String category, final Integer year){
        return transactionService.findLowestSpendForYearByCategory(category, year);
    }
}
