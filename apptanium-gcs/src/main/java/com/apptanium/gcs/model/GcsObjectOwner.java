package com.apptanium.gcs.model;

/**
 * @author saurabh
 */
public class GcsObjectOwner {

  private String id;

  private String name;

  public GcsObjectOwner() {
  }

  public GcsObjectOwner(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GcsObjectOwner that = (GcsObjectOwner) o;

    if (!id.equals(that.id)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    if (id != null) {
      return id.hashCode();
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return "GcsObjectOwner{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
