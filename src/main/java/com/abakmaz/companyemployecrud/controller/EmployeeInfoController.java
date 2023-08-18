package com.abakmaz.companyemployecrud.controller;

import com.abakmaz.companyemployecrud.common.QueryResponse;
import com.abakmaz.companyemployecrud.models.EmployeeInfoDto;
import com.abakmaz.companyemployecrud.services.EmployeeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeInfoController {
    private final EmployeeInfoService employeeInfoService;

    @PostMapping(value = "/save")
    public ResponseEntity<QueryResponse<EmployeeInfoDto>> save(@RequestBody EmployeeInfoDto employeeInfoDto) {

        EmployeeInfoDto createdEmmployeeInfo = employeeInfoService.save(employeeInfoDto);

        EmployeeInfoDto infoDto = new EmployeeInfoDto();
        infoDto.setEmployeeId(createdEmmployeeInfo.getEmployeeId());
        infoDto.setName(createdEmmployeeInfo.getName());
        infoDto.setSurname(createdEmmployeeInfo.getSurname());
        infoDto.setFkCompanyId(createdEmmployeeInfo.getFkCompanyId());

        QueryResponse<EmployeeInfoDto> queryResponse = QueryResponse.createResponse(createdEmmployeeInfo != null, infoDto,"Kayıt Başarılı");
        return ResponseEntity.status(HttpStatus.CREATED).body(queryResponse);
    }
    @GetMapping("/list")
    public ResponseEntity<QueryResponse<List<EmployeeInfoDto>>> listEmployees() {
        List<EmployeeInfoDto> employeeInfoDtos = employeeInfoService.list();

        QueryResponse<List<EmployeeInfoDto>> queryResponse = QueryResponse.createResponse(employeeInfoDtos != null, employeeInfoDtos, "Listeleme Başarılı");
        return ResponseEntity.status(HttpStatus.OK).body(queryResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QueryResponse<EmployeeInfoDto>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeInfoDto updatedEmployeeInfo) {
        EmployeeInfoDto updatedEmployee = employeeInfoService.update(id, updatedEmployeeInfo);
        QueryResponse<EmployeeInfoDto> queryResponse = QueryResponse.createResponse(true, updatedEmployee, "Güncelleme Başarılı");
        return ResponseEntity.status(HttpStatus.OK).body(queryResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<QueryResponse<String>> deleteEmployee(@PathVariable Long id) {
        employeeInfoService.delete(id);
        QueryResponse<String> queryResponse = QueryResponse.createResponse(true, null, "Çalışan Başarıyla Silindi");
        return ResponseEntity.status(HttpStatus.OK).body(queryResponse);
    }
}
