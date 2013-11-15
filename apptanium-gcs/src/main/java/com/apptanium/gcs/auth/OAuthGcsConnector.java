package com.apptanium.gcs.auth;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author saurabh
 */
public class OAuthGcsConnector {
  private static final Logger log = Logger.getLogger(OAuthGcsConnector.class.getName());

  private static final String ACL = "x-goog-acl";
  private static final String PROJECT_ID = "x-goog-project-id";
  private static final String CACHE_CONTROL = "Cache-Control";
  private static final String CONTENT_ENCODING = "Content-Encoding";
  private static final String CONTENT_DISPOSITION = "Content-Disposition";
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String DATE = "Date";
  private static final String HOST = "Host";
  private static final String ETAG = "ETag";
  private final HTTPHeader versionHeader = new HTTPHeader("x-goog-api-version", "2");

  public static final List<String> OAUTH_SCOPES =
          ImmutableList.of("https://www.googleapis.com/auth/devstorage.read_write");

  private static final int READ_LIMIT_BYTES = 8 * 1024 * 1024;

  static final int CHUNK_ALIGNMENT_BYTES = 256 * 1024;

  private final OAuthURLFetchService urlFetch;

  public OAuthGcsConnector(OAuthURLFetchService urlFetch) {
    this.urlFetch = urlFetch;
  }
}
