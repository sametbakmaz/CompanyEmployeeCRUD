package com.abakmaz.companyemployecrud.services;

import com.abakmaz.companyemployecrud.models.EmployeeInfoDto;

import java.util.List;

public interface EmployeeInfoService {
    EmployeeInfoDto save(EmployeeInfoDto employeeInfoDto);

    EmployeeInfoDto update(Long id, EmployeeInfoDto employeeInfoDto);

    List<EmployeeInfoDto> list();

    void delete(Long id);
}
