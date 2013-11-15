package com.apptanium.gcs;

import com.apptanium.gcs.auth.AppIdentityOAuthURLFetchService;
import com.apptanium.gcs.auth.URLFetchUtils;
import com.google.appengine.api.utils.SystemProperty;

/**
 * @author saurabh
 */
public final class ApptaniumGcsServiceFactory {
  private ApptaniumGcsServiceFactory(){}

  public static ApptaniumGcsService getGcsService(String projectId) {
    return new ApptaniumGcsServiceImpl(projectId);
  }

}
