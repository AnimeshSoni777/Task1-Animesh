package com.example.demo.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean is_success;
    private String official_email;
    private T data;
    private String error;

    public ApiResponse(boolean success, String email) {
        this.is_success = success;
        this.official_email = email;
    }

    public ApiResponse(boolean success, String email, T data) {
        this.is_success = success;
        this.official_email = email;
        this.data = data;
    }

    public ApiResponse(boolean success, String email, String error) {
        this.is_success = success;
        this.official_email = email;
        this.error = error;
    }

    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getOfficial_email() {
        return official_email;
    }

    public void setOfficial_email(String official_email) {
        this.official_email = official_email;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
