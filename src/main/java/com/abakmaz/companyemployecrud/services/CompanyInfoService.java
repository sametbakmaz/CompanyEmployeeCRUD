package com.abakmaz.companyemployecrud.services;

import com.abakmaz.companyemployecrud.models.CompanyInfoDto;

import java.util.List;

public interface CompanyInfoService {
    CompanyInfoDto save(CompanyInfoDto companyInfoDto);
    List<CompanyInfoDto> list();
/*    CompanyInfoDto update(Long id, CompanyInfoDto updatedCompanyInfo);*/

    CompanyInfoDto updateCompanyName(Long id, String newCompanyName);

    void delete(Long id);

}
