package fr.il_totore.manadrop.optional;

import java.util.List;
import java.util.function.Consumer;

public interface OptionalList<T> extends List<T> {

    void ifEmpty(Runnable runnable);

    void ifPresent(int i, Consumer<T> consumer);

    void ifSingle(Consumer<T> consumer);

    void ifAllPresent(int start, int end, Consumer<OptionalList<T>> consumer);

    void ifAllPresent(int start, Consumer<OptionalList<T>> consumer);

    void ifAllPresentForEach(int start, int end, Consumer<T> consumer);

    void ifAllPresentForEach(int start, Consumer<T> consumer);

    void ifSizeSuperior(int size, Consumer<OptionalList<T>> consumer);
}
