package com.migsoftware.trentdb.parser;

public class QualifiedNameReference extends Expression {

  private final QualifiedName name;

  public QualifiedNameReference(QualifiedName name) {
    this.name = name;
  }

  public QualifiedName getName() {
    return name;
  }
}
