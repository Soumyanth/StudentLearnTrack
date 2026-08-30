package com.airtribe.learntrack.exception;

import com.airtribe.learntrack.entity.Enrollment;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
