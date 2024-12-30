package com.migsoftware.trentdb.parser;

public class Table extends QueryBody {

  private final QualifiedName name;

  public QualifiedName getName() {
    return name;
  }

  public Table(QualifiedName name) {
    this.name = name;
  }
}
