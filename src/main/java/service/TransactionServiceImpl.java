package service;

import data.TransactionRepository;
import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findTransactionsByCategory(final String category) {
        return transactionRepository.findAll().stream().filter(transaction ->
                transaction.getCategory().equals(category)).collect(Collectors.toList());
    }

    public BigDecimal findTotalOutgoingByCategory(final String category){
        final BigDecimal totalOutgoing = BigDecimal.ZERO;
        final List<Transaction> transactions = findTransactionsByCategory(category);
        return (BigDecimal) transactions.stream().map(transaction -> totalOutgoing.add(transaction.getAmount()));
    }

    @Override
    public BigDecimal findHighestSpendForYearByCategory(final String category, final Integer year){
        final List<Transaction> transactions = findTransactionsByCategory(category);
        final Transaction transaction = transactions.stream().filter(t -> t.getTransactionDate().getYear() == year)
                .max(Comparator.comparing(Transaction::getAmount)).orElseThrow(NoSuchElementException::new);
        return transaction.getAmount();
    }

    @Override
    public BigDecimal findLowestSpendForYearByCategory(final String category, final Integer year){
        final List<Transaction> transactions = findTransactionsByCategory(category);
        final Transaction transaction = transactions.stream().filter(t -> t.getTransactionDate().getYear() == year)
                .min(Comparator.comparing(Transaction::getAmount)).orElseThrow(NoSuchElementException::new);
        return transaction.getAmount();
    }

    @Override
    public BigDecimal findAverageMonthlySpendByCategory(String category) {
        final List<Transaction> transactions = findTransactionsByCategory(category);
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
