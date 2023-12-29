package az.ibrahimshirinov.jdbctemplatesample.config;

import az.ibrahimshirinov.jdbctemplatesample.mapper.DomainMapper;
import az.ibrahimshirinov.jdbctemplatesample.mapper.DtoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public DomainMapper domainMapper() {
        return DomainMapper.INSTANCE;
    }

    @Bean
    public DtoMapper dtoMapper() {
        return DtoMapper.INSTANCE;
    }

}
