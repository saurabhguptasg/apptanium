package com.apptanium.gcs;

import com.apptanium.gcs.model.*;

import java.util.List;

/**
 * @author saurabh
 */
public interface ApptaniumGcsService {
  public GcsObjectListing listObjects(String bucketName) throws GcsException;

  public GcsObjectListing listObjects(String bucketName, String prefix) throws GcsException;

  /**
   * <p>
   * Returns a list of summary information about the objects in the specified
   * bucket. Depending on the request parameters, additional information is returned,
   * such as common prefixes if a delimiter was specified. List
   * results are <i>always</i> returned in lexicographic (alphabetical) order.
   * </p>
   * <p>
   * Because buckets can contain a virtually unlimited number of keys, the
   * complete results of a list query can be extremely large. To manage large
   * result sets, GCS uses pagination to split them into multiple
   * responses. Always check the
   * {@link com.apptanium.gcs.model.GcsObjectListing#isTruncated()} method to see if the returned
   * listing is complete or if additional calls are needed to get
   * more results. Alternatively, use the
   * {@link ApptaniumGcsService#listNextBatchOfObjects(com.apptanium.gcs.model.GcsObjectListing)} method as
   * an easy way to get the next page of object listings.
   * </p>
   * <p>
   * Calling {@link ListObjectsRequest#setDelimiter(String)}
   * sets the delimiter, allowing groups of keys that share the
   * delimiter-terminated prefix to be included
   * in the returned listing. This allows applications to organize and browse
   * their keys hierarchically, similar to how a file system organizes files
   * into directories. These common prefixes can be retrieved
   * through the {@link com.apptanium.gcs.model.GcsObjectListing#getCommonPrefixes()} method.
   * </p>
   * <p>
   * For example, consider a bucket that contains the following keys:
   * <ul>
   * 	<li>"foo/bar/baz"</li>
   * 	<li>"foo/bar/bash"</li>
   * 	<li>"foo/bar/bang"</li>
   * 	<li>"foo/boo"</li>
   * </ul>
   * If calling <code>listObjects</code> with
   * a prefix value of "foo/" and a delimiter value of "/"
   * on this bucket, an <code>ObjectListing</code> is returned that contains one key
   * ("foo/boo") and one entry in the common prefixes list ("foo/bar/").
   * To see deeper into the virtual hierarchy, make another
   * call to <code>listObjects</code> setting the prefix parameter to any interesting
   * common prefix to list the individual keys under that prefix.
   * </p>
   * <p>
   * The total number of keys in a bucket doesn't substantially
   * affect list performance.
   * </p>
   *
   * @param listObjectsRequest
   *            The request object containing all options for listing the
   *            objects in a specified bucket.
   *
   * @return A listing of the objects in the specified bucket, along with any
   *         other associated information, such as common prefixes (if a
   *         delimiter was specified), the original request parameters, etc.
   *
   * @see ApptaniumGcsService#listObjects(String)
   * @see ApptaniumGcsService#listObjects(String, String)
   */
  public GcsObjectListing listObjects(ListObjectsRequest listObjectsRequest) throws GcsException;

  /**
   * <p>
   * Provides an easy way to continue a truncated object listing and retrieve
   * the next page of results.
   * </p>
   * <p>
   * To continue the object listing and retrieve the next page of results,
   * call the initial {@link com.apptanium.gcs.model.GcsObjectListing} from one of the
   * <code>listObjects</code> methods.
   * If truncated
   * (indicated when {@link com.apptanium.gcs.model.GcsObjectListing#isTruncated()} returns <code>true</code>),
   * pass the <code>ObjectListing</code> back into this method
   * in order to retrieve the
   * next page of results. Continue using this method to
   * retrieve more results until the returned <code>ObjectListing</code> indicates that
   * it is not truncated.
   * </p>
   * @param previousGcsObjectListing
   *            The previous truncated <code>ObjectListing</code>.
   *            If a
   *            non-truncated <code>ObjectListing</code> is passed in, an empty
   *            <code>ObjectListing</code> is returned without ever contacting
   *            GCS.
   *
   * @return The next set of <code>ObjectListing</code> results, beginning immediately
   *         after the last result in the specified previous <code>ObjectListing</code>.
   *
   * @see ApptaniumGcsService#listObjects(String)
   * @see ApptaniumGcsService#listObjects(String, String)
   * @see ApptaniumGcsService#listObjects(ListObjectsRequest)
   */
  public GcsObjectListing listNextBatchOfObjects(GcsObjectListing previousGcsObjectListing) throws GcsException;


  public List<GcsBucket> listBuckets() throws GcsException;

  public GcsBucket createBucket(String bucketName);


  public GcsBucketConfig getBucketConfig(String bucketName, GcsBucketConfigType configType) throws GcsException;
}
