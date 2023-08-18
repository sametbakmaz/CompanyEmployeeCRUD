package com.abakmaz.companyemployecrud.controller;

import com.abakmaz.companyemployecrud.models.CompanyInfoDto;
import com.abakmaz.companyemployecrud.common.QueryResponse;
import com.abakmaz.companyemployecrud.services.CompanyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/company")
public class CompanyInfoController {

    private final CompanyInfoService companyInfoService;

    @PostMapping(value = "/save")
    public ResponseEntity<QueryResponse<CompanyInfoDto>> save(@RequestBody CompanyInfoDto companyInfoDto) {

        CompanyInfoDto createdCompanyInfo = companyInfoService.save(companyInfoDto);

        CompanyInfoDto infoDto = new CompanyInfoDto();
        infoDto.setCompanyId(createdCompanyInfo.getCompanyId());
        infoDto.setCompanyName(createdCompanyInfo.getCompanyName());
        infoDto.setId(createdCompanyInfo.getId());

        QueryResponse<CompanyInfoDto> queryResponse = QueryResponse.createResponse(createdCompanyInfo != null, infoDto,"Kayıt Başarılı");
        return ResponseEntity.status(HttpStatus.CREATED).body(queryResponse);
    }
    @GetMapping(value = "/list")
    public ResponseEntity<QueryResponse<List<CompanyInfoDto>>> listCompanies() {
        List<CompanyInfoDto> companyInfoDtos = companyInfoService.list();

        QueryResponse<List<CompanyInfoDto>> queryResponse = QueryResponse.createResponse(companyInfoDtos != null, companyInfoDtos, "Listeleme Başarılı");
        return ResponseEntity.status(HttpStatus.OK).body(queryResponse);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<QueryResponse<CompanyInfoDto>> updateCompany(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String newCompanyName = requestBody.get("companyName");

        CompanyInfoDto updatedCompany = companyInfoService.updateCompanyName(id, newCompanyName);
        QueryResponse<CompanyInfoDto> queryResponse = QueryResponse.createResponse(true, updatedCompany, "Güncelleme Başarılı");
        return ResponseEntity.status(HttpStatus.OK).body(queryResponse);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<QueryResponse<String>> deleteCompany(@PathVariable Long id) {
        companyInfoService.delete(id);
        QueryResponse<String> queryResponse = QueryResponse.createResponse(true, null, "Şirket Başarıyla Silindi");
        return ResponseEntity.status(HttpStatus.OK).body(queryResponse);
    }
}
