package az.ibrahimshirinov.jdbctemplatesample.mapper;

import az.ibrahimshirinov.jdbctemplatesample.domain.Product;
import az.ibrahimshirinov.jdbctemplatesample.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DtoMapper {

    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

   ProductResponse toDto(Product product);
}
