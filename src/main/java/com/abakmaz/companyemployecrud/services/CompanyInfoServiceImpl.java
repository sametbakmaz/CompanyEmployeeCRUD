package com.abakmaz.companyemployecrud.services;

import com.abakmaz.companyemployecrud.mappers.CompanyInfoMapper;
import com.abakmaz.companyemployecrud.models.CompanyInfoDto;
import com.abakmaz.companyemployecrud.models.CompanyInfoEntity;
import com.abakmaz.companyemployecrud.models.EmployeeInfoEntity;
import com.abakmaz.companyemployecrud.repository.CompanyInfoRepository;
import com.abakmaz.companyemployecrud.repository.EmployeeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyInfoServiceImpl implements CompanyInfoService{

    private final CompanyInfoRepository companyInfoRepository;
    private final EmployeeInfoRepository employeeInfoRepository;

    @Override
    public CompanyInfoDto save(CompanyInfoDto companyInfoDto) {

        if (companyInfoDto.getCompanyId() != null) {
            CompanyInfoEntity existingCompany = companyInfoRepository.findByCompanyId(companyInfoDto.getCompanyId());

            if (existingCompany != null) {
                throw new InvalidParameterException("Aynı CompanyId başka bir şirket için kullanılmaktadır!");
            }
        }

        CompanyInfoEntity companyInfoEntity =(toEntity(companyInfoDto));
        companyInfoRepository.save(companyInfoEntity);
        return companyInfoDto;
    }

    @Override
    public List<CompanyInfoDto> list() {
        List<CompanyInfoEntity> companyInfoEntities = companyInfoRepository.findAll();
        List<CompanyInfoDto> companyInfoDtos = new ArrayList<>();

        for (CompanyInfoEntity entity : companyInfoEntities) {
            companyInfoDtos.add(toDto(entity));
        }

        return companyInfoDtos;
    }
/*    @Override
    public CompanyInfoDto update(Long id, CompanyInfoDto updatedCompanyInfo) {
        CompanyInfoEntity existingCompany = companyInfoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Şirket bulunamadı"));

        if (!existingCompany.getCompanyId().equals(updatedCompanyInfo.getCompanyId())) {
            CompanyInfoEntity existingCompanies = companyInfoRepository.findByCompanyId(updatedCompanyInfo.getCompanyId());
            if (existingCompanies != null) {
                throw new InvalidParameterException("Aynı CompanyId başka bir şirket için kullanılmaktadır!");
            }
        }
        updateEntityFields(existingCompany, updatedCompanyInfo);
        companyInfoRepository.save(existingCompany);

        return toDto(existingCompany);
    }*/
    @Override
    public CompanyInfoDto updateCompanyName(Long id, String newCompanyName) { //id ve companyId güncellenmemeli
        CompanyInfoEntity existingCompany = companyInfoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Şirket bulunamadı"));

        existingCompany.setCompanyName(newCompanyName);
        companyInfoRepository.save(existingCompany);

        return toDto(existingCompany);
    }

    @Override
    public void delete(Long id) {
        CompanyInfoEntity existingCompany = companyInfoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Şirket bulunamadı"));

        Long companyId = existingCompany.getCompanyId();
        List<EmployeeInfoEntity> employeesWithSameCompanyId = employeeInfoRepository.findByFkCompanyId(companyId);

        if (employeesWithSameCompanyId != null) {
            throw new IllegalStateException("Şirketin ilişkili çalışanları olduğu için silme işlemi yapılamaz.");
        }

        companyInfoRepository.delete(existingCompany);
    }

    private void updateEntityFields(CompanyInfoEntity existingCompany, CompanyInfoDto updatedCompanyInfo) {
        // Şirket bilgilerini güncelle
        existingCompany.setCompanyName(updatedCompanyInfo.getCompanyName());
        existingCompany.setCompanyId(updatedCompanyInfo.getCompanyId());
    }

    private CompanyInfoDto toDto (CompanyInfoEntity companyInfoEntity) {
        return CompanyInfoMapper.INSTANCE.toDto(companyInfoEntity);
    }
    private CompanyInfoEntity toEntity ( CompanyInfoDto companyInfoDto) {
        return CompanyInfoMapper.INSTANCE.toEntity(companyInfoDto);
    }
}
