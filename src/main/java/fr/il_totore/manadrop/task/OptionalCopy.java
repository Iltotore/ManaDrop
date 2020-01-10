package fr.il_totore.manadrop.task;

import fr.il_totore.manadrop.util.DefaultOptional;
import org.gradle.api.tasks.AbstractCopyTask;
import org.gradle.api.tasks.Copy;

import java.util.function.Supplier;

public class OptionalCopy extends Copy {

    private DefaultOptional<Object[]> optionalFrom = DefaultOptional.empty();
    private DefaultOptional<Object> optionalTo = DefaultOptional.empty();

    @Override
    public AbstractCopyTask from(Object... sourcePaths) {
        this.optionalFrom = DefaultOptional.ofNullable(sourcePaths);
        return this;
    }

    @Override
    public AbstractCopyTask into(Object destDir) {
        this.optionalTo = DefaultOptional.ofNullable(destDir);
        return this;
    }

    public OptionalCopy fromOptional(DefaultOptional<Object[]> optionalFrom) {
        this.optionalFrom = optionalFrom;
        return this;
    }

    public OptionalCopy intoOptional(DefaultOptional<Object> optionalTo) {
        this.optionalTo = optionalTo;
        return this;
    }

    public OptionalCopy fromDefault(Supplier<Object[]> sourcePaths) {
        optionalFrom.defaultValue(sourcePaths);
        return this;
    }

    public OptionalCopy fromDefaultSingle(Supplier<Object> sourcePaths) {
        return fromDefault(() -> new Object[]{sourcePaths.get()});
    }

    public OptionalCopy intoDefault(Supplier<Object> destDir) {
        optionalTo.defaultValue(destDir);
        return this;
    }

    @Override
    protected void copy() {
        super.from(optionalFrom.getOrDefault());
        super.into(optionalTo.getOrDefault());
        super.copy();
    }
}
