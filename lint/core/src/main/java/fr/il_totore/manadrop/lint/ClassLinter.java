package fr.il_totore.manadrop.lint;

import com.strobel.assembler.metadata.TypeDefinition;
import fr.il_totore.manadrop.util.ThrowingFunction;

import java.util.List;

public interface ClassLinter {

    List<Issue> visit(TypeDefinition type);

    String getId();

    ThrowingFunction<TypeDefinition, Class<?>> TYPE_TO_CLASS = type -> Class.forName(type.getFullName());
}
