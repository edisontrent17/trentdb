package com.migsoftware.trentdb.catalog.inmemory;

import com.migsoftware.trentdb.catalog.Catalog;
import com.migsoftware.trentdb.catalog.CatalogEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InMemoryCatalog implements Catalog {

  private static final Logger logger = Logger.getLogger(InMemoryCatalog.class.getName());


  List<CatalogEntry> entries = new ArrayList<>();

  @Override
  public void addEntry(CatalogEntry catalogEntry) {
    logger.log(Level.INFO, "Adding Catalog Entry", catalogEntry);
    entries.add(catalogEntry);
  }

  @Override
  public String name() {
    return "inmemory";
  }

  @Override
  public boolean isSystemCatalog() {
    return false;
  }

  @Override
  public Set<CatalogEntry> allEntries() {
    return Set.of();
  }
}
