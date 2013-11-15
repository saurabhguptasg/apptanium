package com.apptanium.gcs;

import com.apptanium.gcs.auth.AppIdentityOAuthURLFetchService;
import com.apptanium.gcs.auth.URLFetchUtils;
import com.google.appengine.api.utils.SystemProperty;

/**
 * Use this to create an instance of ApptaniumGcsService.
 * A service is specific to the project id.
 *
 * @author saurabh
 */
public final class ApptaniumGcsServiceFactory {
  private ApptaniumGcsServiceFactory(){}

  public static ApptaniumGcsService getGcsService(String projectId) {
    return new ApptaniumGcsServiceImpl(projectId);
  }

}
