package com.abakmaz.companyemployecrud.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "EMPLOYEE_INFO")
public class EmployeeInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint", nullable = false)
    private Long id;

    @Column(name = "EMPLOYEE_NAME", length = 500)
    private String name;

    @Column(name = "EMPLOYEE_SURNAME", length = 500)
    private String surname;

    @Column(name = "EMPLOYEE_ID", columnDefinition = "bigint", nullable = false)
    private Long employeeId;

    @Column(name = "FK_COMPANY_ID")
    private Long fkCompanyId;

}
