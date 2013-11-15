package com.apptanium.gcs.model;

import org.w3c.dom.Document;

/**
 * @author saurabh
 */
public abstract class GcsBucketConfig {

  public abstract void parseResult(Document document);


  public static final class Acl extends GcsBucketConfig {

    @Override
    public void parseResult(Document document) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  public static final class Cors extends GcsBucketConfig {

    @Override
    public void parseResult(Document document) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  public static final class Lifecycle extends GcsBucketConfig {

    @Override
    public void parseResult(Document document) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  public static final class Logging extends GcsBucketConfig {

    @Override
    public void parseResult(Document document) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  public static final class Versioning extends GcsBucketConfig {

    @Override
    public void parseResult(Document document) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

}
