package com.apptanium.gcs.model;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * @author saurabh
 */
public class Parser {

  public static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

  public static GcsException hasException(Document document) {
    if(document.getNodeName().equals("Error")) {
      String code = document.getElementsByTagName("Code").item(0).getFirstChild().getTextContent().trim();
      String message = document.getElementsByTagName("Message").item(0).getFirstChild().getTextContent().trim();
      return new GcsException(code, message);
    }
    return null;
  }

  public static List<GcsBucket> parseBucketList(Document document) throws GcsException {
    GcsException exception = null;
    if((exception = hasException(document)) != null) {
      throw exception;
    }
    return null;
  }

  public static GcsObjectListing parseObjectListing(Document document) throws GcsException {
    GcsException exception = null;
    if((exception = hasException(document)) != null) {
      throw exception;
    }
    GcsObjectListing bucketObjectList = new GcsObjectListing();
    bucketObjectList.setBucketName(getIfNotNull(document, "Name"));
    bucketObjectList.setPrefix(getIfNotNull(document, "Prefix"));
    bucketObjectList.setMarker(getIfNotNull(document, "Marker"));
    bucketObjectList.setNextMarker(getIfNotNull(document, "NextMarker"));
    String truncStr = getIfNotNull(document, "IsTruncated");
    bucketObjectList.setTruncated(Boolean.parseBoolean(truncStr));

    NodeList nodeList = document.getElementsByTagName("Contents");
    int length = nodeList.getLength();
    for (int i = 0; i < length; i++) {
      Node node = nodeList.item(i);
      if(node.getNodeType() == Node.ELEMENT_NODE) {
        bucketObjectList.getObjectSummaries().add(parseBucketObject(node));
      }
    }

    NodeList commonPrefixesNodes = document.getElementsByTagName("CommonPrefixes");
    int cpnLength = commonPrefixesNodes.getLength();
    for (int i = 0; i < cpnLength; i++) {
      Node node = commonPrefixesNodes.item(i);
      GcsCommonPrefix commonPrefix = parseCommonPrefix(node);
      if(commonPrefix != null) {
        bucketObjectList.getCommonPrefixes().add(commonPrefix);
      }
    }
    return bucketObjectList;
  }

  private static GcsObjectSummary parseBucketObject(Node node) {
    GcsObjectSummary bucketObject = new GcsObjectSummary();
    NodeList nodeList = node.getChildNodes();
    int length = nodeList.getLength();
    for (int i = 0; i < length; i++) {
      Node curr = nodeList.item(i);
      if(curr.getNodeType() == Node.ELEMENT_NODE) {
        String name = curr.getNodeName();
        switch (name) {
          case "Key":
            bucketObject.setKey(curr.getFirstChild().getTextContent().trim());
            break;
          case "Generation":
            bucketObject.setGeneration(Long.parseLong(curr.getFirstChild().getTextContent().trim()));
            break;
          case "MetaGeneration":
            bucketObject.setMetaGeneration(Long.parseLong(curr.getFirstChild().getTextContent().trim()));
            break;
          case "LastModified":
            bucketObject.setLastModified(formatter.parseDateTime(curr.getFirstChild().getTextContent()).toDate());
            break;
          case "ETag":
            String eTag = curr.getFirstChild().getTextContent().trim();
            if(eTag.startsWith("\"") && eTag.endsWith("\"")) {
              eTag = eTag.substring(1, eTag.length() -1);
            }
            bucketObject.seteTag(eTag);
            break;
          case "Size":
            bucketObject.setSize(Long.parseLong(curr.getFirstChild().getTextContent().trim()));
            break;
          case "Owner":
            GcsObjectOwner owner = new GcsObjectOwner();
            NodeList ownerNodeChildList = curr.getChildNodes();
            for (int j = 0; j < ownerNodeChildList.getLength(); j++) {
              Node ownerCurr = ownerNodeChildList.item(j);
              if(ownerCurr.getNodeName().equals("ID")) {
                owner.setId(ownerCurr.getFirstChild().getTextContent().trim());
              }
            }
            bucketObject.setOwner(owner);
            break;
        }
      }
    }
    return bucketObject;
  }

  private static GcsCommonPrefix parseCommonPrefix(Node node) {
    NodeList list = node.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node curr = list.item(i);
      if(curr.getNodeType() == Node.ELEMENT_NODE && curr.getNodeName().equals("Prefix")) {
        return new GcsCommonPrefix(curr.getFirstChild().getTextContent().trim());
      }
    }
    return null;
  }


  private static String getIfNotNull(Document document, String tagName) {
    NodeList list = document.getElementsByTagName(tagName);
    if(list.getLength() > 0) {
      Node node = list.item(0);
      Node fc = node.getFirstChild();
      if(fc == null) {
        return null;
      }
      else if(fc.getNodeType() == Node.TEXT_NODE) {
        return fc.getTextContent().trim();
      }
    }
    return null;
  }

  public static GcsBucketConfig parseBucketConfig(Document document) throws GcsException {
    throw new RuntimeException("Not implemented");
  }
}
