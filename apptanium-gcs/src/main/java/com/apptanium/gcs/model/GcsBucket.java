package com.apptanium.gcs.model;

import java.util.Date;

/**
 * @author saurabh
 */
public class GcsBucket {

  private GcsObjectOwner owner;

  private String name;

  private Date creationDate;

  public GcsBucket() {
  }

  public GcsBucket(GcsObjectOwner owner, String name, Date creationDate) {
    this.owner = owner;
    this.name = name;
    this.creationDate = creationDate;
  }

  public GcsObjectOwner getOwner() {
    return owner;
  }

  public void setOwner(GcsObjectOwner owner) {
    this.owner = owner;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }
}
