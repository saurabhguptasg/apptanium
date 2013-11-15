package com.apptanium.gcs.model;

/**
 * @author saurabh
 */
public enum AclTypes {
  Private("private"),
  PublicRead("public-read"),
  PublicReadWrite("public-read-write"),
  Authenticated("authenticated-read"),
  BucketOwnerRead("bucket-owner-read"),
  BucketOwnerFullControl("bucket-owner-full-control"),
  ;
  private final String value;

  private AclTypes(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
