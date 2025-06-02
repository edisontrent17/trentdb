package com.migsoftware.trentdb.catalog;

import java.util.Set;

public interface Catalog {


  void addEntry(CatalogEntry catalogEntry);

  String name();

  boolean isSystemCatalog();

  Set<CatalogEntry> allEntries();
}
