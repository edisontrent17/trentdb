package com.migsoftware.trentdb.parser;


public class SelectColumn extends Node {

    private final Expression expression;

    public SelectColumn(Expression expression) {
        this.expression = expression;
    }
}
