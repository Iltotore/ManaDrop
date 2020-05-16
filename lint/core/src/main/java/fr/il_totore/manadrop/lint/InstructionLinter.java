package fr.il_totore.manadrop.lint;

import com.strobel.assembler.metadata.MethodDefinition;
import com.strobel.assembler.metadata.TypeDefinition;
import com.strobel.decompiler.DecompilerContext;
import com.strobel.decompiler.ast.AstBuilder;
import com.strobel.decompiler.ast.AstOptimizer;
import com.strobel.decompiler.ast.Block;
import com.strobel.decompiler.ast.Expression;

import java.util.ArrayList;
import java.util.List;

public interface InstructionLinter extends MethodLinter {

    @Override
    default List<Issue> visitMethod(TypeDefinition type, MethodDefinition method) {
        List<Issue> issues = new ArrayList<>();
        Block methodAst = new Block();
        DecompilerContext context = new DecompilerContext();

        context.setCurrentType(type);
        context.setCurrentMethod(method);

        methodAst.getBody().addAll(AstBuilder.build(method.getBody(), true, context));

        AstOptimizer.optimize(context, methodAst);

        methodAst.getChildrenAndSelfRecursive(Expression.class, this::filter)
                .forEach(child -> issues.addAll(visitInstruction(type, child)));
        return issues;
    }

    default boolean filter(Expression expression) {
        return true;
    }

    List<Issue> visitInstruction(TypeDefinition type, Expression node);
}
