package az.grow.lab.jdbctemplatetask4.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper getMapper()  {
        return new ModelMapper();
    }
}
