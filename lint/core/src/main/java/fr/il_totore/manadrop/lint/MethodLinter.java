package fr.il_totore.manadrop.lint;

import com.strobel.assembler.metadata.MethodDefinition;
import com.strobel.assembler.metadata.TypeDefinition;

import java.util.ArrayList;
import java.util.List;

public interface MethodLinter extends ClassLinter {
    @Override
    default List<Issue> visit(TypeDefinition type) {
        List<Issue> issues = new ArrayList<>();
        type.getDeclaredMethods().forEach(method -> issues.addAll(visitMethod(type, method)));
        return issues;
    }

    List<Issue> visitMethod(TypeDefinition type, MethodDefinition method);
}
