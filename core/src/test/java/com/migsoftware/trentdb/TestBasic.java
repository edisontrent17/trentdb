package com.migsoftware.trentdb;

public class TestBasic {

  public void testBasicSelect() {
    TrentDb trentDb = new TrentDb();

    trentDb.query("select * from 'customers.csv'");
  }
}
