package com.test.dto;

public enum Status {

    OK(0), SESSION_NOT_FOUND(1), GAME_NOT_AUTHIRIZED(2), SIGNATURE_INVALID(3);

   private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
