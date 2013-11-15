package com.apptanium.gcs.auth;

import com.google.appengine.api.urlfetch.*;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * taken from google's gcs java library
 * @author google
 */
public abstract class AbstractOAuthURLFetchService implements OAuthURLFetchService {
  private static final URLFetchService URLFETCH = URLFetchServiceFactory.getURLFetchService();

  private final HTTPHeader versionHeader = new HTTPHeader("x-goog-api-version", "2");

  private final String projectId;
  private final HTTPHeader projectIdHeader;

  AbstractOAuthURLFetchService(String projectId) {
    this.projectId = projectId;
    this.projectIdHeader = new HTTPHeader("x-goog-project-id", projectId);
  }

  protected abstract String getAuthorization();

  private HTTPRequest authorizeRequest(HTTPRequest req) {
    req = URLFetchUtils.copyRequest(req);
    req.setHeader(new HTTPHeader("Authorization", getAuthorization()));
    req.setHeader(projectIdHeader);
    req.setHeader(versionHeader);
    return req;
  }

  @Override
  public HTTPResponse fetch(HTTPRequest req) throws IOException {
    return URLFETCH.fetch(authorizeRequest(req));
  }

  @Override
  public Future<HTTPResponse> fetchAsync(HTTPRequest req) {
    return URLFETCH.fetchAsync(authorizeRequest(req));
  }

}
