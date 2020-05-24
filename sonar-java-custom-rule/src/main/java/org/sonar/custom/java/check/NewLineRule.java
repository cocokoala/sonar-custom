/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.custom.java.check;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;

@Rule(
        key = "NewLineRule",
        name = "notice Line Feed(\\n) and Cartridge Return(\\r)",
        description = "when write outputstream to  file，be careful deal with notice Line Feed(\\n) and Cartridge Return(\\r)",
        priority = Priority.INFO,
        tags = {"convention"})
public class NewLineRule extends BaseTreeVisitor implements JavaFileScanner {

//  private static final String DEFAULT_VALUE = "Inject";

  private JavaFileScannerContext context;

  /**
   * Name of the annotation to avoid. Value can be set by users in Quality profiles.
   * The key
   */
//  @RuleProperty(
//    defaultValue = DEFAULT_VALUE,
//    description = "Name of the annotation to avoid, without the prefix @, for instance 'Override'")
//  protected String name;
  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
    System.out.println(PrinterVisitor.print(context.getTree()));
  }
    public void visitLiteral(LiteralTree tree) {
//        System.out.print("start");
//        System.out.println(tree.token().column());
//        System.out.println(tree.token().line());
//        System.out.println( tree.token().text());
//        System.out.println(tree.value());
        boolean newfeed = tree.token().text().indexOf("\"\\n\"") >=0?true:false;
        boolean carriageReturn  =  tree.token().text().indexOf("\"\\r\"") >=0?true:false;
        if (newfeed ^ carriageReturn) {
            context.reportIssue(this, tree, String.format("please confirm using \\r\\n "));
            // Noncompliant {{please confirm using \r\n }}
        }
//        System.out.print("end");
    }

//  @Override
//  public void visitMethodInvocation(MethodInvocationTree tree) {
//      MemberSelectExpressionTree  memberSelectExpressionTree =(MemberSelectExpressionTree) tree.methodSelect();
//      boolean isOutputStream = memberSelectExpressionTree.expression().symbolType().isSubtypeOf("java.io.OutputStream");
////      boolean isWrite =
//
//
//
//    scan(tree.methodSelect());
//    scan(tree.typeArguments());
//    scan(tree.arguments());
//  }
//
//  @Override
//  public void visitMethod(MethodTree tree) {
//    List<AnnotationTree> annotations = tree.modifiers().annotations();
//    for (AnnotationTree annotationTree : annotations) {
//      if (annotationTree.annotationType().is(Tree.Kind.IDENTIFIER)) {
//        IdentifierTree idf = (IdentifierTree) annotationTree.annotationType();
//        System.out.println(idf.name());
//
////        if (idf.name().equals(name)) {
////          context.reportIssue(this, idf, String.format("Avoid using annotation @%s", name));
////        }
//      }
//    }
//
//    // The call to the super implementation allows to continue the visit of the AST.
//    // Be careful to always call this method to visit every node of the tree.
//    super.visitMethod(tree);
//  }
}
