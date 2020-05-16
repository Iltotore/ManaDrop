package fr.il_totore.manadrop.util;

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowingSupplier<T> extends Supplier<T> {

    default T get() {
        try {
            return getThrow();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    T getThrow() throws Exception;
}
