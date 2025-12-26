package com.migsoftware.trentdb.parser;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import org.antlr.v4.runtime.misc.NotNull;

import com.migsoftware.trentdb.parser.SqlBaseParser.ExpressionContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.QueryContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.QueryNoWithContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.QuerySpecificationContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.SelectSingleContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.SingleStatementContext;
import com.migsoftware.trentdb.parser.SqlBaseParser.StringLiteralContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class AstBuilder extends SqlBaseBaseVisitor<Node> {

  @Override
  public Node visitQueryNoWith(QueryNoWithContext ctx) {
    QueryBody term = (QueryBody) visit(ctx.queryTerm());
    if (term instanceof QuerySpecification) {
      QuerySpecification querySpecification = (QuerySpecification) term;
      return new Query(new QuerySpecification(querySpecification.getSelect(), querySpecification.getFrom()));
    }
    return super.visitQueryNoWith(ctx);
  }

  @Override
  public Node visitQuerySpecification(QuerySpecificationContext ctx) {
    Optional<Relation> from = Optional.empty();
    List<Relation> relations = new ArrayList<>();
    for (SqlBaseParser.RelationContext relationContext : ctx.relation()) {
      relations.add((Relation) visit(relationContext));
    }
    checkJoin(relations);
    if (!relations.isEmpty()) {
      from = Optional.of(relations.get(0));
    }

    List<SelectItem> selectItemList = new ArrayList<>();
    for (SqlBaseParser.SelectItemContext itemContext : ctx.selectItem()) {
      selectItemList.add((SelectItem) visit(itemContext));
    }
    return new QuerySpecification(
        new Select(false, selectItemList),
        from
    );
  }


  private void checkJoin(List<Relation> relations) {
    if (relations.size() > 1) {
      throw new UnsupportedOperationException("Multi relation queries are not supported");
    }
  }

  @Override
  public Node visitQuery(QueryContext ctx) {
    Query body = (Query) visit(ctx.queryNoWith());
    return new Query(body.getQueryBody());
  }

  @Override
  public Node visitSingleStatement(SingleStatementContext ctx) {
    return visit(ctx.statement());
  }

  @Override
  public Node visitSingleExpression(@NotNull SqlBaseParser.SingleExpressionContext context) {
    return visit(context.expression());
  }

  @Override
  public Node visitColumnReference(SqlBaseParser.ColumnReferenceContext ctx) {
    return new QualifiedNameReference(getQualifiedName(ctx.qualifiedName()));
  }

  @Override
  public Node visitSelectSingle(SelectSingleContext ctx) {
    return new SingleColumn((Expression) visit(ctx.expression()), Optional.empty());
  }

  @Override
  public Node visitStringLiteral(StringLiteralContext ctx) {
    System.out.println("Visiting literal");
    return new StringLiteral(ctx.STRING().getText());
  }

  @Override
  public Node visitSampledRelation(SqlBaseParser.SampledRelationContext ctx) {
    Relation child = (Relation) visit(ctx.aliasedRelation());
    if (ctx.TABLESAMPLE() == null) {
      return child;
    }
    return super.visitSampledRelation(ctx);
  }

  @Override
  public Node visitAliasedRelation(SqlBaseParser.AliasedRelationContext ctx) {
    Relation rel = (Relation) visit(ctx.relationPrimary());
    if (ctx.identifier() == null) {
      return rel;
    }
    return super.visitAliasedRelation(ctx);
  }

  @Override
  public Node visitTableName(SqlBaseParser.TableNameContext ctx) {
    return new Table(getQualifiedName(ctx.qualifiedName()));
  }

  private static QualifiedName getQualifiedName(SqlBaseParser.QualifiedNameContext context) {
    List<String> parts = context
        .identifier().stream()
        .map(ParseTree::getText)
        .collect(Collectors.toList());

    return new QualifiedName(parts);
  }
}
