package com.apptanium.gcs.test;

import com.apptanium.gcs.ApptaniumGcsService;
import com.apptanium.gcs.ApptaniumGcsServiceFactory;
import com.apptanium.gcs.model.GcsBucketConfigType;
import com.apptanium.gcs.model.GcsException;
import com.apptanium.gcs.model.GcsObjectListing;
import com.apptanium.gcs.model.ListObjectsRequest;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;

import java.io.IOException;

/**
 * @author saurabh
 */
public class RemoteTest {
  protected RemoteApiOptions options;
  protected RemoteApiInstaller remoteApiInstaller;
  protected ApptaniumGcsService apptaniumGcsService;

  public RemoteTest(String host, int port, String projectId, String username, String password) throws IOException {
    options = new RemoteApiOptions()
            .server(host, port)
            .credentials(username, password)
            .remoteApiPath("/_ah/remote_api");
    remoteApiInstaller = new RemoteApiInstaller();
    remoteApiInstaller.install(options);
    options.reuseCredentials(username,
                             remoteApiInstaller.serializeCredentials());


    apptaniumGcsService = ApptaniumGcsServiceFactory.getGcsService(projectId);

//    testListBuckets();
    testListObjects("<<bucket name>>");
    testListObjectsWithPrefixes("<<bucket name>>", "/");
    testBucketConfig("<<bucket name>>", GcsBucketConfigType.ACL);
    testBucketConfig("<<bucket name>>", GcsBucketConfigType.CORS);
    testBucketConfig("<<bucket name>>", GcsBucketConfigType.LIFECYCLE);
    testBucketConfig("<<bucket name>>", GcsBucketConfigType.LOGGING);
    testBucketConfig("<<bucket name>>", GcsBucketConfigType.VERSIONING);
  }

  private void testListObjects(String bucketName) {
    try {
      GcsObjectListing objectListing = apptaniumGcsService.listObjects(bucketName);
      System.out.println("objectListing = " + objectListing);
    }
    catch (GcsException e) {
      System.out.println("e = " + e);
    }
  }

  private void testListBuckets() {
    try {
      apptaniumGcsService.listBuckets();
    }
    catch (GcsException e) {
      System.out.println("e = " + e);
    }
  }

  private void testListObjectsWithPrefixes(String bucketName, String delimiter) {
    System.out.println("----- RemoteTest.testListObjectsWithPrefixes");
    ListObjectsRequest request = new ListObjectsRequest(bucketName, null, null, delimiter, null);
    try {
      GcsObjectListing objectListing = apptaniumGcsService.listObjects(request);
      System.out.println("objectListing = " + objectListing);
    }
    catch (GcsException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
  }

  private void testBucketConfig(String bucketName, GcsBucketConfigType configType) {
    System.out.println("-----\n-----\n----- RemoteTest.testBucketConfig: bucketName = [" + bucketName + "], configType = [" + configType + "]");
    try {
      apptaniumGcsService.getBucketConfig(bucketName, configType);
    }
    catch (GcsException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
  }


  public static void main(String[] args) throws IOException {
    new RemoteTest(args[0], Integer.parseInt(args[1]), args[2], args[3], args[4]);
  }
}
