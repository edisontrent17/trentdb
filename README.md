# Trentdb

In Memory Sql Query Engine written in Java inspired by DuckDB

# Goals

* Educational. I want to learn about databases.
* A side project which is fun and technically challenging and which can potentially lead to something more.
* Minimal dependencies. Only relies on ANTLR4 and Google Guava
* Must be able to cater to both analytical and transactional workloads
* Vectorized query execution
* Transactions.

# Details

* Grammar is based on sqlite and trino/presto .
* ANTLR4 for parsing.

# TODO

* Define the grammar.
* Create the AST (Abstract Syntax Tree)
* Implement `Show Tables`