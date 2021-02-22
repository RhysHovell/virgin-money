package data;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionVO {
    @Id
    private Long id;
    private LocalDate transactionDate;
    private String vendor;
    private String type;
    private BigDecimal amount;
    private String category;
}
