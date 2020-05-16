package fr.il_totore.manadrop.optional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class OptionalArrayList<T> extends ArrayList<T> implements OptionalList<T> {

    public OptionalArrayList() {
        super();
    }

    public OptionalArrayList(Collection<T> collection) {
        super(collection);
    }

    @Override
    public void ifEmpty(Runnable runnable) {
        if(isEmpty()) runnable.run();
    }

    @Override
    public void ifPresent(int i, Consumer<T> consumer) {
        if(size() > i && get(i) != null) consumer.accept(get(i));
    }

    @Override
    public void ifSingle(Consumer<T> consumer) {
        if(size() == 1 && get(0) != null) consumer.accept(get(0));
    }

    @Override
    public void ifAllPresent(int start, int end, Consumer<OptionalList<T>> consumer) {
        if(start >= 0 && start < size() && end <= size()) consumer.accept(subList(start, end));
    }

    @Override
    public void ifAllPresent(int start, Consumer<OptionalList<T>> consumer) {
        ifAllPresent(start, size(), consumer);
    }

    @Override
    public void ifAllPresentForEach(int start, int end, Consumer<T> consumer) {
        ifAllPresent(start, end, list -> list.forEach(consumer));
    }

    @Override
    public void ifAllPresentForEach(int start, Consumer<T> consumer) {
        ifAllPresentForEach(start, size(), consumer);
    }

    @Override
    public void ifSizeSuperior(int size, Consumer<OptionalList<T>> consumer) {
        if(size() > size) consumer.accept(this);
    }

    @Override
    public OptionalList<T> subList(int fromIndex, int toIndex) {
        return new OptionalArrayList<>(super.subList(fromIndex, toIndex));
    }
}
