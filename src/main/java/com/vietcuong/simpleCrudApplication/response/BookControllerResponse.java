package com.vietcuong.simpleCrudApplication.response;

import com.vietcuong.simpleCrudApplication.common.ResponseStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class BookControllerResponse<T> {
    private String statusCode;
    private String description;
    private T content;

    public <T> BookControllerResponse<T> initializeResponse() {
        BookControllerResponse<T> response = new BookControllerResponse<>();
        response.setStatusCode(ResponseStatus.BookControllerResponse.REQUEST_SUCCESS.getStatusCode());
        response.setDescription(ResponseStatus.BookControllerResponse.REQUEST_SUCCESS.getDescription());
        return response;
    }

    public BookControllerResponse<Map<String, String>> createSuccessResponse(String successMessage) {
        BookControllerResponse<Map<String, String>> response = new BookControllerResponse<>();
        response.setStatusCode(ResponseStatus.BookControllerResponse.REQUEST_SUCCESS.getStatusCode());
        response.setDescription(ResponseStatus.BookControllerResponse.REQUEST_SUCCESS.getDescription());
        Map<String, String> message = new HashMap<>();
        message.put("message", successMessage);
        response.setContent(message);
        return response;
    }

    public BookControllerResponse<Map<String, String>> createErrorResponse(ResponseStatus.BookControllerResponse errorEnum, Exception e) {
        BookControllerResponse<Map<String, String>> response = new BookControllerResponse<>();
        response.setStatusCode(errorEnum.getStatusCode());
        response.setDescription(errorEnum.getDescription());
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        response.setContent(error);
        return response;
    }
}
