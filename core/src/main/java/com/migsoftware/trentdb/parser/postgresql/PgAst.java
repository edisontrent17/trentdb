package com.migsoftware.trentdb.parser.postgresql;

import com.migsoftware.trentdb.execution.PhysicalOperator;
import com.migsoftware.trentdb.parser.CaseInsensitiveStream;
import com.migsoftware.trentdb.parser.Node;
import com.migsoftware.trentdb.parser.PostgreSQLLexer;
import com.migsoftware.trentdb.parser.PostgreSQLLexerBase;
import com.migsoftware.trentdb.parser.PostgreSQLParser;
import com.migsoftware.trentdb.parser.PostgreSQLParserBaseVisitor;
import com.migsoftware.trentdb.parser.ShowCatalogs;
import com.migsoftware.trentdb.parser.SqlBaseLexer;
import com.migsoftware.trentdb.parser.SqlBaseParser;
import com.migsoftware.trentdb.planner.LogicalOperator;
import com.migsoftware.trentdb.planner.LogicalPlanner;
import com.migsoftware.trentdb.planner.Planner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

public class PgAst extends PostgreSQLParserBaseVisitor<Node> {

  @Override
  public Node visitSelectstmt(PostgreSQLParser.SelectstmtContext ctx) {
    return visit(ctx.select_no_parens());
  }

  @Override
  public Node visitRoot(PostgreSQLParser.RootContext ctx) {
    PostgreSQLParser.StmtblockContext stmtblock = ctx.stmtblock();
    Node result = visitStmtblock(stmtblock);
    return result;
  }

  @Override
  public Node visitStmtblock(PostgreSQLParser.StmtblockContext ctx) {
    Node result = super.visitStmtblock(ctx);
    return result;
  }

  @Override
  public Node visitStmtmulti(PostgreSQLParser.StmtmultiContext ctx) {
    Node result = super.visitStmtmulti(ctx);
    return result;
  }

  @Override
  public Node visitVariableshowstmt(PostgreSQLParser.VariableshowstmtContext ctx) {

    TerminalNode node = ctx.ALL();
    if (node != null) {
      return new ShowCatalogs();
    } else {
      throw new UnsupportedOperationException("Only SHOW CATALOGS is supported");
    }
  }

  @Override
  public Node visitSelect_no_parens(PostgreSQLParser.Select_no_parensContext ctx) {
    PostgreSQLParser.For_locking_clauseContext lockContext = ctx.for_locking_clause();
    if (lockContext != null) {
      throw new UnsupportedOperationException("Select FOR updates and other Lock clauses are not supported yet");
    }
    PostgreSQLParser.With_clauseContext withCtx = ctx.with_clause();
    if (withCtx != null) {
      throw new UnsupportedOperationException("Common table expressions are not supported yet");
    }

    return super.visitSelect_no_parens(ctx);
  }

  public static void main(String[] args) {
    String sql = "SHOW ALL";
    PostgreSQLLexerBase lexer = new PostgreSQLLexer(new ANTLRInputStream(sql));
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    PostgreSQLParser parser = new PostgreSQLParser(tokenStream);
    ParserRuleContext tree = parser.root();

    Node ast = new PgAst().visit(tree);

    LogicalPlanner planner = new LogicalPlanner();
    LogicalOperator operator = planner.createPlan(ast);

    System.out.println(operator);


  }
}
