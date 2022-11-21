package com.ayotycoon.daos.responses.enums;

public enum SUCCESS_RESPONSES {
    SUCCESSFUL_CREATION("SUCCESSFUL_CREATION"),
    GENERIC("GENERIC"),


    ;

    private String value;

    SUCCESS_RESPONSES(String value) {
        this.value = value;
    }

    SUCCESS_RESPONSES() {

    }

    @Override
    public String toString() {
        return this.name() + " - " + value;
    }
}
