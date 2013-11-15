package com.apptanium.gcs;

/**
 * @author saurabh
 */
public final class ApptaniumGcsServiceConfig {
  private final String projectId;
  private final String bucketName;

  public ApptaniumGcsServiceConfig(String projectId, String bucketName) {
    this.projectId = projectId;
    this.bucketName = bucketName;
  }

  public String getProjectId() {
    return projectId;
  }

  public String getBucketName() {
    return bucketName;
  }

  @Override
  public String toString() {
    return "ApptaniumGcsServiceConfig{" +
            "projectId='" + projectId + '\'' +
            ", bucketName='" + bucketName + '\'' +
            '}';
  }
}
