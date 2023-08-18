package com.abakmaz.companyemployecrud.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeInfoDto {

    private Long id;
    private String name;
    private String surname;
    private Long employeeId;
    private Long fkCompanyId;
}
