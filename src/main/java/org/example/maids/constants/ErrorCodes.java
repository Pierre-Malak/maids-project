package org.example.maids.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    RETURN_DATE_ERROR("RT02"),
    ENTITY_NOTFOUND_ERROR("ET05"),
    OBJECT_VALIDATION_ERROR("OBJ04");
    private final String errorCode;
    ErrorCodes(String code){
        this.errorCode = code;
    }
}
