package az.ibrahimshirinov.jdbctemplatesample.mapper;

import az.ibrahimshirinov.jdbctemplatesample.domain.Product;
import az.ibrahimshirinov.jdbctemplatesample.dto.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DomainMapper {

    DomainMapper INSTANCE = Mappers.getMapper(DomainMapper.class);

    @Mapping(target = "createdAt",
            expression = "java(java.time.LocalDateTime.now())")
    Product toDomain(ProductRequest dto);
}
