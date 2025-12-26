package com.migsoftware.trentdb.parser;

public class StringLiteral extends Expression {

    private final String symbol;


    public StringLiteral(String symbol) {
        this.symbol = symbol;
    }
}
