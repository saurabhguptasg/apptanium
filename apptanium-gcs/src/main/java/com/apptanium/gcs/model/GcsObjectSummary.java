package com.apptanium.gcs.model;

import java.util.Date;

/**
 * Contains the summary of an object stored in an Amazon S3 bucket. This object
 * doesn't contain contain the
 * object's full metadata or any of its contents.
 *
 * @author saurabh
 */
public class GcsObjectSummary {

  protected String key;

  protected Long generation;

  protected Long metaGeneration;

  protected Date lastModified;

  protected String eTag;

  protected Long size;

  protected GcsObjectOwner owner;

  public GcsObjectSummary() {
  }

  public GcsObjectSummary(String key, Long generation, Long metaGeneration, Date lastModified, String eTag, Long size, GcsObjectOwner owner) {
    this.key = key;
    this.generation = generation;
    this.metaGeneration = metaGeneration;
    this.lastModified = lastModified;
    this.eTag = eTag;
    this.size = size;
    this.owner = owner;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Long getGeneration() {
    return generation;
  }

  public void setGeneration(Long generation) {
    this.generation = generation;
  }

  public Long getMetaGeneration() {
    return metaGeneration;
  }

  public void setMetaGeneration(Long metaGeneration) {
    this.metaGeneration = metaGeneration;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public String geteTag() {
    return eTag;
  }

  public void seteTag(String eTag) {
    this.eTag = eTag;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public GcsObjectOwner getOwner() {
    return owner;
  }

  public void setOwner(GcsObjectOwner owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "GcsObjectSummary{" +
            "key='" + key + '\'' +
            ", generation=" + generation +
            ", metaGeneration=" + metaGeneration +
            ", lastModified=" + lastModified +
            ", eTag='" + eTag + '\'' +
            ", size=" + size +
            ", owner=" + owner +
            '}';
  }
}
