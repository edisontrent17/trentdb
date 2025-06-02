package com.migsoftware.trentdb.parser.postgresql;

import com.migsoftware.trentdb.data.DataChunk;
import com.migsoftware.trentdb.execution.PhysicalOperator;
import com.migsoftware.trentdb.parser.Node;
import com.migsoftware.trentdb.parser.PostgreSQLLexer;
import com.migsoftware.trentdb.parser.PostgreSQLLexerBase;
import com.migsoftware.trentdb.parser.PostgreSQLParser;
import com.migsoftware.trentdb.parser.PostgreSQLParserBaseVisitor;
import com.migsoftware.trentdb.parser.ShowCatalogs;
import com.migsoftware.trentdb.planner.LogicalOperator;
import com.migsoftware.trentdb.planner.LogicalPlanner;
import com.migsoftware.trentdb.planner.PhysicalPlanCreator;
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
  public Node visitTableelementlist(PostgreSQLParser.TableelementlistContext ctx) {
    return super.visitTableelementlist(ctx);
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
  public Node visitTable_(PostgreSQLParser.Table_Context ctx) {
    return super.visitTable_(ctx);
  }

  @Override
  public Node visitVariableshowstmt(PostgreSQLParser.VariableshowstmtContext ctx) {

    TerminalNode node = ctx.CATALOGS();
    if (node != null) {
      return new ShowCatalogs();
    } else {
      throw new UnsupportedOperationException("Only SHOW CATALOGS is supported");
    }
  }

  @Override
  public Node visitSelect_clause(PostgreSQLParser.Select_clauseContext ctx) {
    ctx.simple_select_intersect();
    return super.visitSelect_clause(ctx);
  }

  @Override
  public Node visitSimple_select_intersect(PostgreSQLParser.Simple_select_intersectContext ctx) {
    ctx.simple_select_pramary();
    return super.visitSimple_select_intersect(ctx);
  }

  @Override
  public Node visitSimple_select_pramary(PostgreSQLParser.Simple_select_pramaryContext ctx) {
    ctx.target_list();
    return super.visitSimple_select_pramary(ctx);
  }

  @Override
  public Node visitTarget_list(PostgreSQLParser.Target_listContext ctx) {
    ctx.target_el();
    return super.visitTarget_list(ctx);
  }

  @Override
  public Node visitTarget_star(PostgreSQLParser.Target_starContext ctx) {
    return super.visitTarget_star(ctx);
  }


  @Override
  public Node visitRelation_expr(PostgreSQLParser.Relation_exprContext ctx) {
    ctx.qualified_name();
    return super.visitRelation_expr(ctx);
  }

  @Override
  public Node visitColid(PostgreSQLParser.ColidContext ctx) {
    ctx.identifier().Identifier().toString();
    return super.visitColid(ctx);
  }

  @Override
  public Node visitQualified_name(PostgreSQLParser.Qualified_nameContext ctx) {
    ctx.colid();
    return super.visitQualified_name(ctx);
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
    ctx.select_clause();

    return super.visitSelect_no_parens(ctx);
  }

  public static void main(String[] args) {
    String sql = "select * from test";
    PostgreSQLLexerBase lexer = new PostgreSQLLexer(new ANTLRInputStream(sql));
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    PostgreSQLParser parser = new PostgreSQLParser(tokenStream);
    ParserRuleContext tree = parser.root();

    Node ast = new PgAst().visit(tree);

    LogicalPlanner planner = new LogicalPlanner();
    LogicalOperator operator = planner.createPlan(ast);

    PhysicalPlanCreator physicalPlanCreator = new PhysicalPlanCreator();
    PhysicalOperator physicalOperator = physicalPlanCreator.createPlan(operator);

    do {
      DataChunk chunk = physicalOperator.getData();
      chunk.print();
    } while (!physicalOperator.done());


  }
}
