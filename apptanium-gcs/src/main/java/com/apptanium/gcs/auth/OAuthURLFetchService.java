package com.apptanium.gcs.auth;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * taken from google's gcs java library
 * @author google
 */
public interface OAuthURLFetchService {

  public HTTPResponse fetch(HTTPRequest req) throws IOException;
  public Future<HTTPResponse> fetchAsync(HTTPRequest request);

}
