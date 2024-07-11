package com.vietcuong.simpleCrudApplication.common;

public class ResponseStatus {
    public enum BookControllerResponse {
        REQUEST_SUCCESS("00", "REQUEST_SUCCESS"),
        EMPTY_DATABASE("01", "EMPTY_DATABASE"),
        BOOK_NOT_EXIST("02", "BOOK_NOT_EXIST"),
        BOOK_EXISTED("03", "BOOK_EXISTED");

        private final String statusCode;
        private final String description;

        BookControllerResponse(String statusCode, String description) {
            this.statusCode = statusCode;
            this.description = description;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getDescription() {
            return description;
        }
    }
}
