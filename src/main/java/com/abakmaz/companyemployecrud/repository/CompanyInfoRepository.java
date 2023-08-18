package com.abakmaz.companyemployecrud.repository;

import com.abakmaz.companyemployecrud.models.CompanyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfoEntity, Long> {
    CompanyInfoEntity findByCompanyId(Long companyId);
}
