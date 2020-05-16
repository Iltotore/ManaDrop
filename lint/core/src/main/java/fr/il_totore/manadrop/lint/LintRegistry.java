package fr.il_totore.manadrop.lint;

import java.util.List;

public class LintRegistry {

    private List<ClassLinter> linters;

    public LintRegistry register(ClassLinter linter) {
        linters.add(linter);
        return this;
    }

    public void unregister(ClassLinter linter) {
        linters.remove(linter);
    }

    public List<ClassLinter> getLinters() {
        return linters;
    }
}
