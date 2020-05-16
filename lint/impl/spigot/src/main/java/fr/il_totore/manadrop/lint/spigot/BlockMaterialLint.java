package fr.il_totore.manadrop.lint.spigot;

import com.strobel.assembler.metadata.*;
import com.strobel.decompiler.ast.AstCode;
import com.strobel.decompiler.ast.Expression;
import fr.il_totore.manadrop.lint.ClassLinter;
import fr.il_totore.manadrop.lint.InstructionLinter;
import fr.il_totore.manadrop.lint.Issue;
import fr.il_totore.manadrop.lint.IssueType;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.List;

public class BlockMaterialLint implements InstructionLinter {

    @Override
    public List<Issue> visitInstruction(TypeDefinition type, Expression expression) {

        MethodDefinition setTypeMethod = MetadataSystem.instance()
                .lookupType(Block.class.getTypeName())
                .resolve()
                .getDeclaredMethods()
                .stream()
                .filter(method -> method.getName().equals("setType"))
                .findAny().get();

        if(((MethodReference) expression.getOperand()).resolve() == setTypeMethod) {
            FieldDefinition reference = ((FieldReference) expression.getArguments().get(1).getOperand()).resolve();
            if(!Material.valueOf(reference.getName()).isBlock()) {
                return Collections.singletonList(Issue.of(IssueType.Spigot.BLOCK_MATERIAL, ClassLinter.TYPE_TO_CLASS.apply(type)));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean filter(Expression expression) {
        return expression.getCode() == AstCode.InvokeInterface;
    }

    @Override
    public String getId() {
        return "blockMaterial";
    }
}
