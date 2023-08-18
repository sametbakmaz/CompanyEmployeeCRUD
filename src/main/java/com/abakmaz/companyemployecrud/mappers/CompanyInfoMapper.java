package com.abakmaz.companyemployecrud.mappers;

import com.abakmaz.companyemployecrud.models.CompanyInfoDto;
import com.abakmaz.companyemployecrud.models.CompanyInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyInfoMapper {
    CompanyInfoMapper INSTANCE = Mappers.getMapper(CompanyInfoMapper.class);

    CompanyInfoEntity toEntity(CompanyInfoDto companyInfoDto);

    CompanyInfoDto toDto(CompanyInfoEntity companyInfoEntity);
}
