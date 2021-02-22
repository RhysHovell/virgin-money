package mapping;

import data.TransactionVO;
import entity.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration("transactionMappingConfiguration")
public class TransactionMappingConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public TransactionVO convertVoToEntity() {
        return (TransactionVO) modelMapper().
                createTypeMap(Transaction.class, TransactionVO.class).implicitMappings();
    }
}
