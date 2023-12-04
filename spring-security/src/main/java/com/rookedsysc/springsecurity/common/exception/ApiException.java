package com.rookedsysc.springsecurity.common.exception;

import com.rookedsysc.springsecurity.common.error.ErrorCodeIfs;

public class ApiException extends RuntimeException  {
    private ErrorCodeIfs ErrorCodeIfs;
    private String errorDescription;

    public ApiException(ErrorCodeIfs ErrorCodeIfs) {
        super(ErrorCodeIfs.getDescription());
        this.ErrorCodeIfs = ErrorCodeIfs;
        this.errorDescription = ErrorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs ErrorCodeIfs, String errorCodeDescription) {
        super(errorCodeDescription);
        this.ErrorCodeIfs = ErrorCodeIfs;
        this.errorDescription = errorCodeDescription;
    }

    public ApiException(ErrorCodeIfs ErrorCodeIfs, Throwable throwable) {
        super(throwable);
        this.ErrorCodeIfs = ErrorCodeIfs;
        this.errorDescription = ErrorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs ErrorCodeIfs, Throwable throwable, String errorCodeDescription) {
        super(throwable);
        this.ErrorCodeIfs = ErrorCodeIfs;
        this.errorDescription = errorCodeDescription;
    }

    public ErrorCodeIfs getErrorCodeIfs() {
        return this.ErrorCodeIfs;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }
}