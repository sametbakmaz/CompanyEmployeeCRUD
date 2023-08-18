package com.abakmaz.companyemployecrud.mappers;

import com.abakmaz.companyemployecrud.models.EmployeeInfoDto;
import com.abakmaz.companyemployecrud.models.EmployeeInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeInfoMapper {
    EmployeeInfoMapper INSTANCE = Mappers.getMapper(EmployeeInfoMapper.class);

    EmployeeInfoEntity toEntity(EmployeeInfoDto employeeInfoDto);

    EmployeeInfoDto toDto(EmployeeInfoEntity employeeInfoEntity);
}
