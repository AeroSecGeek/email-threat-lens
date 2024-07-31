package com.aerosecgeek.emailthreatlensservice.core.exception;

public class AttachmentIsEmail extends RuntimeException {
    public AttachmentIsEmail(String message) {
        super(message);
    }
}
