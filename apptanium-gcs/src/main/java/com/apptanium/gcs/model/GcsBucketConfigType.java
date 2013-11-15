package com.apptanium.gcs.model;

/**
 * @author saurabh
 */
public enum GcsBucketConfigType {
  ACL("acl", GcsBucketConfig.Acl.class),
  CORS("cors", GcsBucketConfig.Cors.class),
  LIFECYCLE("lifecycle", GcsBucketConfig.Lifecycle.class),
  LOGGING("logging", GcsBucketConfig.Logging.class),
  VERSIONING("versioning", GcsBucketConfig.Versioning.class),
  ;
  private final String param;
  private final Class<? extends GcsBucketConfig> configClass;

  private GcsBucketConfigType(String param, Class<? extends GcsBucketConfig> configClass) {
    this.param = param;
    this.configClass = configClass;
  }

  public String getParam() {
    return param;
  }

  public Class<? extends GcsBucketConfig> getConfigClass() {
    return configClass;
  }
}
