package com.ahmed.Library.responses;

import java.util.List;

public class ValidationErrorResponse {
    private int httpStatus;
    private List<String> errors;

    public ValidationErrorResponse(int httpStatus, List<String> errors) {
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}