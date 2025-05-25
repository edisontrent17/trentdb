package com.migsoftware.trentdb.parser;


import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowCatalogs extends Node {

  private static final Logger logger = Logger.getLogger(ShowCatalogs.class.getName());

  public ShowCatalogs() {
    logger.log(Level.INFO, "Inside Show Catalogs");
  }
}
