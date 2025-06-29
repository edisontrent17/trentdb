package com.migsoftware.trentdb.parser;

public class QuotedIdentifier extends Node {


  private final String name;


  public QuotedIdentifier(String name) {
    this.name = name;
  }

  public boolean isCsv() {
    return name.endsWith(".csv");
  }


}
