package com.apptanium.gcs.model;

/**
 * @author saurabh
 */
public class GcsException extends Exception {
  private final String errorCode;

  public GcsException(String errorCode, String message) {
    super(message);    //To change body of overridden methods use File | Settings | File Templates.
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  @Override
  public String toString() {
    return "GcsException{" +
            "errorCode='" + errorCode + '\'' +
            ", message='" + getMessage() + '\'' +
            '}';
  }
}
