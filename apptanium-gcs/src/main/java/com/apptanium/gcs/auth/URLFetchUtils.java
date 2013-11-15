package com.apptanium.gcs.auth;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.List;

/**
 * taken from google's gcs java library
 * @author google
 */
public final class URLFetchUtils {

  private URLFetchUtils() {}

  static String describeRequest(HTTPRequest req) {
    StringBuilder b = new StringBuilder(req.getMethod() + " " + req.getURL());
    for (HTTPHeader h : req.getHeaders()) {
      b.append("\n" + h.getName() + ": " + h.getValue());
    }
    b.append("\n\n");
    if (req.getPayload() == null) {
      b.append("no content");
    } else {
      b.append(req.getPayload().length + " bytes of content");
    }
    return b + "\n";
  }

  static String describeResponse(HTTPResponse resp, boolean includeBody) {
    StringBuilder b = new StringBuilder(resp.getResponseCode()
                                                + " with " + resp.getContent().length + " bytes of content");
    for (HTTPHeader h : resp.getHeadersUncombined()) {
      b.append("\n" + h.getName() + ": " + h.getValue());
    }
    if (includeBody) {
      b.append("\n" + new String(resp.getContent(), Charsets.UTF_8));
    } else {
      b.append("\n<content elided>");
    }
    return b + "\n";
  }

  static String describeRequestAndResponse(HTTPRequest req, HTTPResponse resp,
                                           boolean includeResponseBody) {
    return "Request: " + describeRequest(req)
            + "\nResponse: " + describeResponse(resp, includeResponseBody);
  }

  /** Gets the values of all headers with the name {@code headerName}. */
  static List<String> getHeaders(HTTPResponse resp, String headerName) {
    ImmutableList.Builder<String> b = ImmutableList.builder();
    for (HTTPHeader h : resp.getHeadersUncombined()) {
      if (headerName.equalsIgnoreCase(h.getName())) {
        b.add(h.getValue());
      }
    }
    return b.build();
  }

  /**
   * Checks that exactly one header named {@code headerName} is present and
   * returns its value.
   */
  static String getSingleHeader(HTTPResponse resp, String headerName) {
    return Iterables.getOnlyElement(getHeaders(resp, headerName));
  }

  static HTTPRequest copyRequest(HTTPRequest in) {
    HTTPRequest out = new HTTPRequest(in.getURL(), in.getMethod(), in.getFetchOptions());
    for (HTTPHeader h : in.getHeaders()) {
      out.addHeader(h);
    }
    out.setPayload(in.getPayload());
    return out;
  }

}
