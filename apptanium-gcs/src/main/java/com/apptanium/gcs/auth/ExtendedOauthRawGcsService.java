package com.apptanium.gcs.auth;

import com.google.appengine.api.urlfetch.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @author saurabh
 */
public class ExtendedOauthRawGcsService {


  public static final String STORAGE_BASE_URL = "https://storage.googleapis.com/";
  public static final long DEFAULT_TIMEOUT_MILLIS = 10000l; //ten second timeout

  public static URL makeUrl(String bucketName, Map<String,String> queryParams) {
    StringBuilder s = new StringBuilder(STORAGE_BASE_URL + bucketName);
    if(queryParams != null && queryParams.size() > 0) {
      s.append("?");
      boolean prefixAnd = false;
      for (Map.Entry<String, String> entry : queryParams.entrySet()) {
        if(prefixAnd) {
          s.append("&");
        }
        else {
          prefixAnd = true;
        }
        s.append(entry.getKey());
        if(entry.getValue() != null) {
          s.append("=").append(entry.getValue());
        }
      }
    }
    try {
      return new URL(s.toString());
    } catch (MalformedURLException e) {
      throw new RuntimeException("Internal error: " + s, e);
    }
  }

  public static HTTPRequest makeRequest(String bucketName,
                                        Map<String,String> queryParams,
                                        HTTPMethod method,
                                        long timeoutMillis) {
    return new HTTPRequest(makeUrl(bucketName, queryParams), method,
                           FetchOptions.Builder.disallowTruncate()
                                       .doNotFollowRedirects()
                                       .validateCertificate()
                                       .setDeadline(timeoutMillis / 1000.0));
  }

  public static HTTPRequest makeGetRequest() throws MalformedURLException {
    return new HTTPRequest(new URL(STORAGE_BASE_URL), HTTPMethod.GET,
                           FetchOptions.Builder.disallowTruncate()
                                       .doNotFollowRedirects()
                                       .validateCertificate()
                                       .setDeadline(DEFAULT_TIMEOUT_MILLIS / 1000.0));
  }


  public static HTTPResponse doRequest(HTTPRequest request, OAuthURLFetchService urlFetchService) throws IOException {
    HTTPResponse response = urlFetchService.fetch(request);
    return response;
  }
}
