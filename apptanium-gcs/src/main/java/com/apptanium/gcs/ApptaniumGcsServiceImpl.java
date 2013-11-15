package com.apptanium.gcs;

import com.apptanium.gcs.auth.AppIdentityOAuthURLFetchService;
import com.apptanium.gcs.auth.ExtendedOauthRawGcsService;
import com.apptanium.gcs.auth.OAuthURLFetchService;
import com.apptanium.gcs.model.*;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.common.collect.ImmutableList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author saurabh
 */
public class ApptaniumGcsServiceImpl implements ApptaniumGcsService {

  private final OAuthURLFetchService urlFetchService;

  public static final List<String> OAUTH_SCOPES =
          ImmutableList.of("https://www.googleapis.com/auth/devstorage.read_write");

  public ApptaniumGcsServiceImpl(String projectId) {
    this.urlFetchService = new AppIdentityOAuthURLFetchService(OAUTH_SCOPES, projectId);
  }

  @Override
  public GcsObjectListing listObjects(String bucketName) throws GcsException {
    try {
      HTTPRequest request = ExtendedOauthRawGcsService.makeRequest(bucketName, null, HTTPMethod.GET,
                                                                   ExtendedOauthRawGcsService.DEFAULT_TIMEOUT_MILLIS);
      HTTPResponse response = ExtendedOauthRawGcsService.doRequest(request, urlFetchService);
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(new ByteArrayInputStream(response.getContent()));
      return Parser.parseObjectListing(document);
    }
    catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    catch (GcsException e) {
      throw e;
    }
    return null;
  }

  @Override
  public GcsObjectListing listObjects(String bucketName, String prefix) throws GcsException {
    ListObjectsRequest request = new ListObjectsRequest(bucketName, prefix, null, null, 100);
    return listObjects(request);
  }

  @Override
  public GcsObjectListing listObjects(ListObjectsRequest listObjectsRequest) throws GcsException {
    Map<String,String> params = new HashMap<>();
    if(listObjectsRequest.getPrefix() != null) {
      params.put("prefix", listObjectsRequest.getPrefix());
    }
    if(listObjectsRequest.getDelimiter() != null) {
      params.put("delimiter", listObjectsRequest.getDelimiter());
    }
    if(listObjectsRequest.getMaxKeys() != null) {
      params.put("max-keys", listObjectsRequest.getMaxKeys().toString());
    }
    if(listObjectsRequest.getMarker() != null) {
      params.put("marker", listObjectsRequest.getMarker());
    }
    System.out.println("params = " + params);
    try {
      HTTPRequest request = ExtendedOauthRawGcsService.makeRequest(listObjectsRequest.getBucketName(),
                                                                   params,
                                                                   HTTPMethod.GET,
                                                                   ExtendedOauthRawGcsService.DEFAULT_TIMEOUT_MILLIS);
      HTTPResponse response = ExtendedOauthRawGcsService.doRequest(request, urlFetchService);
      System.out.println("new String(response.getContent()) = " + new String(response.getContent()));
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(new ByteArrayInputStream(response.getContent()));
      return Parser.parseObjectListing(document);
    }
    catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    return null;
  }

  @Override
  public GcsObjectListing listNextBatchOfObjects(GcsObjectListing previousGcsObjectListing) throws GcsException {
    return listObjects(new ListObjectsRequest(previousGcsObjectListing.getBucketName(),
                                              previousGcsObjectListing.getPrefix(),
                                              previousGcsObjectListing.getNextMarker(),
                                              previousGcsObjectListing.getDelimiter(),
                                              previousGcsObjectListing.getMaxKeys()));
  }

  @Override
  public List<GcsBucket> listBuckets() throws GcsException {
    try {
      HTTPRequest request = ExtendedOauthRawGcsService.makeGetRequest();
      request.addHeader(new HTTPHeader("Accept-Encoding", "identity"));
      HTTPResponse response = ExtendedOauthRawGcsService.doRequest(request, urlFetchService);
      System.out.println("new String(response.getContent()) = " + new String(response.getContent()));
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(new ByteArrayInputStream(response.getContent()));
      return Parser.parseBucketList(document);
    }
    catch (SAXException | ParserConfigurationException | IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public GcsBucket createBucket(String bucketName) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public GcsBucketConfig getBucketConfig(String bucketName, GcsBucketConfigType configType) throws GcsException {
    Map<String,String> params = new HashMap<>();
    params.put(configType.getParam(), null);
    System.out.println("params = " + params);
    try {
      HTTPRequest request = ExtendedOauthRawGcsService.makeRequest(bucketName,
                                                                   params,
                                                                   HTTPMethod.GET,
                                                                   ExtendedOauthRawGcsService.DEFAULT_TIMEOUT_MILLIS);
      HTTPResponse response = ExtendedOauthRawGcsService.doRequest(request, urlFetchService);
      System.out.println("new String(response.getContent()) = " + new String(response.getContent()));
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(new ByteArrayInputStream(response.getContent()));
      return Parser.parseBucketConfig(document);
    }
    catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    return null;
  }
}
