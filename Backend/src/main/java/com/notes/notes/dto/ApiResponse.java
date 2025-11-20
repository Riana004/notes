package com.notes.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private T data;
    private ErrorDetails error;
    
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data, null);
    }
    
    public static <T> ApiResponse<T> error(String code, String message, String details) {
        return new ApiResponse<>("error", null, new ErrorDetails(code, message, details));
    }
}
