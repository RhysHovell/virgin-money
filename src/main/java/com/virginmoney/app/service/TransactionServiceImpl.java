package com.virginmoney.app.service;

import com.virginmoney.app.data.TransactionRepository;
import com.virginmoney.app.data.TransactionVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TransactionVO> findTransactionsByCategory(final String category) {
        return transactionRepository.findAll().stream().filter(transaction ->
                transaction.getCategory().equals(category))
                .map(transaction -> modelMapper.map(transaction, TransactionVO.class))
                .collect(Collectors.toList());
    }

    public BigDecimal findTotalOutgoingByCategory(final String category){
        final BigDecimal totalOutgoing = BigDecimal.ZERO;
        final List<TransactionVO> transactions = findTransactionsByCategory(category);
        return (BigDecimal) transactions.stream().map(transaction -> totalOutgoing.add(transaction.getAmount()));
    }

    @Override
    public BigDecimal findHighestSpendForYearByCategory(final String category, final Integer year){
        final List<TransactionVO> transactions = findTransactionsByCategory(category);
        final TransactionVO transaction = transactions.stream().filter(t -> t.getTransactionDate().getYear() == year)
                .max(Comparator.comparing(TransactionVO::getAmount))
                .orElseThrow(NoSuchElementException::new);
        return transaction.getAmount();
    }

    @Override
    public BigDecimal findLowestSpendForYearByCategory(final String category, final Integer year){
        final List<TransactionVO> transactions = findTransactionsByCategory(category);
        final TransactionVO transaction = transactions.stream().filter(t -> t.getTransactionDate().getYear() == year)
                .min(Comparator.comparing(TransactionVO::getAmount))
                .orElseThrow(NoSuchElementException::new);
        return transaction.getAmount();
    }

    @Override
    public BigDecimal findAverageMonthlySpendByCategory(String category) {
        final List<TransactionVO> transactions = findTransactionsByCategory(category);
        return transactions.stream()
                .map(TransactionVO::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
