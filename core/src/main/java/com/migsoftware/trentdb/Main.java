package com.migsoftware.trentdb;

import com.migsoftware.trentdb.execution.PhysicalOperator;
import com.migsoftware.trentdb.parser.AstBuilder;
import com.migsoftware.trentdb.parser.Node;
import com.migsoftware.trentdb.planner.LogicalOperator;
import com.migsoftware.trentdb.planner.LogicalPlanner;
import com.migsoftware.trentdb.planner.PhysicalPlanner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

import com.migsoftware.trentdb.parser.CaseInsensitiveStream;
import com.migsoftware.trentdb.parser.SqlBaseLexer;
import com.migsoftware.trentdb.parser.SqlBaseParser;

public class Main {

  public static void main(String[] args) throws Exception {
    String sql = "SELECT name from test";
    SqlBaseLexer lexer = new SqlBaseLexer(new CaseInsensitiveStream(new ANTLRInputStream(sql)));
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    SqlBaseParser parser = new SqlBaseParser(tokenStream);
    ParserRuleContext tree = parser.singleStatement();

    System.out.println(tree);
    Node parsedQuery = new AstBuilder().visit(tree);

    System.out.println("ParsedQuery " + parsedQuery);

    LogicalOperator logicalOperator = new LogicalPlanner().createPlan(parsedQuery);

    PhysicalOperator physicalOperator = new PhysicalPlanner().createPlan(logicalOperator);


  }
}