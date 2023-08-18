package com.abakmaz.companyemployecrud.repository;

import com.abakmaz.companyemployecrud.models.EmployeeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfoEntity, Long> {
    List<EmployeeInfoEntity> findByEmployeeId(Long employee);

    List<EmployeeInfoEntity> findByFkCompanyId(Long companyId);
}
