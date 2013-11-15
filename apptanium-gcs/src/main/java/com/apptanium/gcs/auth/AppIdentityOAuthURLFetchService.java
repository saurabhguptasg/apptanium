package com.apptanium.gcs.auth;

import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * taken from google's gcs java library
 * @author google
 */
public final class AppIdentityOAuthURLFetchService extends AbstractOAuthURLFetchService {

  private final List<String> oauthScopes;

  public AppIdentityOAuthURLFetchService(List<String> oauthScopes, String projectId) {
    super(projectId);
    this.oauthScopes = ImmutableList.copyOf(oauthScopes);
  }

  @Override
  protected String getAuthorization() {
    String accessToken = AppIdentityServiceFactory.getAppIdentityService()
                                                  .getAccessToken(oauthScopes).getAccessToken();
    if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) {
      throw new RuntimeException(
              this + ": The access token from the development environment won't work: " + accessToken);
    }
    return "Bearer " + accessToken;
  }

}
