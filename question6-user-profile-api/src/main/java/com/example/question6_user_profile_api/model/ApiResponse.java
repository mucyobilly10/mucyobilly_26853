package com.example.question6_user_profile_api.model;

public class ApiResponse<T> {
    
    private boolean success;
    private String message;
    private T data;
    
    // constructor with all fields
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    // default constructor
    public ApiResponse() {
    }
    
    // getters and setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
