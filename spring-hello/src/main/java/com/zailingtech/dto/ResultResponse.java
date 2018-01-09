package com.zailingtech.dto;

public class ResultResponse<T> {
    private int code;
    private String message;
    private T data;
    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 500;

    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    public static final String DEFAULT_ERROR_MESSAGE = "UNKNOWN ERROR";

    public static <DATA> ResultResponse createSuccessResponse(DATA data){
        return new ResultResponse<>(SUCCESS_CODE,DEFAULT_SUCCESS_MESSAGE,data);
    }


    public static <DATA> ResultResponse<DATA> createFailureResponse() {
        return new  ResultResponse<>(ERROR_CODE,DEFAULT_ERROR_MESSAGE);
    }




    public ResultResponse() {

    }

    public ResultResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
