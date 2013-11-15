# intro
Apptaium utilities for Google App Engine
========================================

apptainium-gcs
--------------

This is a package that allows basic access to Google Cloud Service. The
default `GcsService` for java that Google publishes only allows for creating, retrieving and deleting specific objects.
It does not permit the code to browse a bucket.

The apptainium-gcs code can be dropped into your app engine project to allow programmatic browsing of a given bucket,
including with prefixes, delimiters, and with pagination.

See the class `RemoteTest` in the test area that uses the app engine remote api to utilize a functioning
app engine application to query a GCS bucket for objects.

The `GcsObjectListing` contains a list of `GcsObjectSummary` objects that can be used with the GcsService that
Google provides to retrieve or delete those objects, or get their metadata.

In that sense, this library is a complement to the GcsService provided by Google, not a replacement for it (yet).

At this time, bucket creation and bucket listing is not operational. However, given a bucket to which your
app engine application has access, this library can be used to recurse over its objects.

Feedback, comments and feature requests welcome!