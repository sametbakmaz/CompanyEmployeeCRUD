package com.abakmaz.companyemployecrud.services;

import com.abakmaz.companyemployecrud.mappers.EmployeeInfoMapper;
import com.abakmaz.companyemployecrud.models.CompanyInfoEntity;
import com.abakmaz.companyemployecrud.models.EmployeeInfoDto;
import com.abakmaz.companyemployecrud.models.EmployeeInfoEntity;
import com.abakmaz.companyemployecrud.repository.CompanyInfoRepository;
import com.abakmaz.companyemployecrud.repository.EmployeeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

    private final EmployeeInfoRepository employeeInfoRepository;
    private final CompanyInfoRepository companyInfoRepository;

    @Override
    public EmployeeInfoDto save(EmployeeInfoDto employeeInfoDto) {

        validate(employeeInfoDto);
        validateEmployeeId(employeeInfoDto.getEmployeeId());
        validateCompanyId(employeeInfoDto.getFkCompanyId());
        EmployeeInfoEntity employeeInfoEntity = toEntity(employeeInfoDto);
        employeeInfoRepository.save(employeeInfoEntity);

        return employeeInfoDto;
    }

    @Override
    public EmployeeInfoDto update(Long id, EmployeeInfoDto employeeInfoDto) {

        validate(employeeInfoDto);
        validateCompanyId(employeeInfoDto.getFkCompanyId());

        EmployeeInfoEntity existingEmployee = employeeInfoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Çalışan bulunamadı"));

        EmployeeInfoEntity updatedEmployee = toEntity(employeeInfoDto);
        updatedEmployee.setId(existingEmployee.getId()); // Güncelleme için varolan ID korunmalı
        employeeInfoRepository.save(updatedEmployee);
        return toDto(updatedEmployee);
    }
    @Override
    public List<EmployeeInfoDto> list() {
        List<EmployeeInfoEntity> employeeInfoEntities = employeeInfoRepository.findAll();
        List<EmployeeInfoDto> employeeInfoDtos = new ArrayList<>();

        for (EmployeeInfoEntity entity : employeeInfoEntities) {
            employeeInfoDtos.add(toDto(entity));
        }

        return employeeInfoDtos;
    }
    @Override
    public void delete(Long id) {
        EmployeeInfoEntity existingEmployee = employeeInfoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Çalışan bulunamadı"));

        employeeInfoRepository.delete(existingEmployee);
    }

    private void validateEmployeeId(Long employeeId) {
        if (employeeId != null) {
            List<EmployeeInfoEntity> existingEmployee = employeeInfoRepository.findByEmployeeId(employeeId);
            if (!existingEmployee.isEmpty()) {
                throw new InvalidParameterException("Aynı EmployeeId başka bir çalışan için kullanılmaktadır!");
            }
        }
    }

    private CompanyInfoEntity validateCompanyId(Long companyInfoEntity) {
        CompanyInfoEntity company = companyInfoRepository.findByCompanyId(companyInfoEntity);
        if (company == null) {
            throw new InvalidParameterException("Geçersiz CompanyId, böyle bir şirket bulunamadı!");
        }
        return company;
    }
    private void validate(EmployeeInfoDto employeeInfoDto) {
        if (StringUtils.isEmpty(employeeInfoDto.getEmployeeId())) {
            throw new InvalidParameterException("Employee ID Boş olamaz");
        }

        if (StringUtils.isEmpty(employeeInfoDto.getName())) {
            throw new InvalidParameterException("Name boş olamaz.");
        }

        if (StringUtils.isEmpty(employeeInfoDto.getSurname())) {
            throw new InvalidParameterException("Surname boş olamaz.");
        }

        if (StringUtils.isEmpty(employeeInfoDto.getFkCompanyId())) {
            throw new InvalidParameterException("Company ID boş  olamaz.");
        }
    }

    private EmployeeInfoDto toDto (EmployeeInfoEntity employeeInfoEntity) {
        return EmployeeInfoMapper.INSTANCE.toDto(employeeInfoEntity);
    }
    private EmployeeInfoEntity toEntity ( EmployeeInfoDto employeeInfoDto) {
        return EmployeeInfoMapper.INSTANCE.toEntity(employeeInfoDto);
    }
}
