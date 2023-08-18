package com.abakmaz.companyemployecrud.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QueryResponse<T> {
    private String status;
    private T data;
    private String message;
    private Integer exceptionCode;

    public static <T> QueryResponse<T> createResponse(boolean isSuccess, T data, String message) {
        QueryResponse<T> queryResponse = new QueryResponse<>();
        queryResponse.setStatus(isSuccess ? "success" : "fail");
        queryResponse.setData(data);
        queryResponse.setMessage(message);
        queryResponse.setExceptionCode(null);
        return queryResponse;
    }

    public static <T> QueryResponse<T> createResponse(boolean isSuccess, T data, String message, Integer exceptionCode) {
        QueryResponse<T> queryResponse = new QueryResponse<>();
        queryResponse.setStatus(isSuccess ? "success" : "fail");
        queryResponse.setData(data);
        queryResponse.setMessage(message);
        queryResponse.setExceptionCode(exceptionCode);
        return queryResponse;
    }
}