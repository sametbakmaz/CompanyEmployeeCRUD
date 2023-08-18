package com.abakmaz.companyemployecrud.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    COMPANY_NOT_FOUND(1001),
    INVALID_COMPANY_ID(1002),
    DELETE_FAIL(1003);

    private final int code;


}