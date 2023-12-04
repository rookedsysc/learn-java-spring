package com.rookedsysc.springsecurity.common.error;

public interface ErrorCodeIfs {
	public int getHttpStatusCode();
	public int getErrorCode();
	public String getDescription();
}