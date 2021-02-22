package com.virginmoney.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode
@ToString
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate transactionDate;
    private String vendor;
    private String type;
    private BigDecimal amount;
    private String category;

}
