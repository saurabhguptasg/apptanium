package com.apptanium.gcs.model;

/**
 * @author saurabh
 */
public class GcsCommonPrefix {
  private String prefix;

  public GcsCommonPrefix() {
  }

  public GcsCommonPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getPrefix() {
    return prefix;
  }

  @Override
  public String toString() {
    return "GcsCommonPrefix{" +
            "prefix='" + prefix + '\'' +
            '}';
  }
}
