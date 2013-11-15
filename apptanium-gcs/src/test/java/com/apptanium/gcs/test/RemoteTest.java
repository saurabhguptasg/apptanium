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
    //NOTE: change <<bucket name>> with the actual name of your target test bucket
    testListObjects("<<bucket name>>");
    testListObjectsWithPrefixes("<<bucket name>>", "/");
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

  public static void main(String[] args) throws IOException {
    new RemoteTest(args[0], //the endpoint: e.g. my-app.appspot.com
                   Integer.parseInt(args[1]), //the port, use 443
                   args[2], //the project id, e.g. my-app
                   args[3], //your username for the app, you must have admin rights on the application
                   args[4]); //your password for the username
  }
}
