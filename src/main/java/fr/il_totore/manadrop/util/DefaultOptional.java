package fr.il_totore.manadrop.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DefaultOptional<T> {

    private Optional<T> optional;
    private Supplier<T> defaultValue = () -> null;

    public DefaultOptional(Optional<T> optional, Supplier<T> defaultValue) {
        this.optional = optional;
        defaultValue(defaultValue);
    }

    public DefaultOptional(Optional<T> optional, T defaultValue) {
        this.optional = optional;
        defaultValue(defaultValue);
    }

    public DefaultOptional(Optional<T> optional) {
        this(optional, (T) null);
    }

    public Optional<T> getOptional() {
        return optional;
    }

    public Supplier<T> getDefaultValue() {
        return defaultValue;
    }

    public DefaultOptional<T> defaultValue(Supplier<T> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public DefaultOptional<T> defaultValue(T defaultValue) {
        return defaultValue(() -> defaultValue);
    }

    public T get() {
        return optional.get();
    }

    public T getOrDefault() {
        return orElse(defaultValue.get());
    }

    public boolean isPresent() {
        return optional.isPresent();
    }

    public void ifPresent(Consumer<? super T> consumer) {
        optional.ifPresent(consumer);
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        return optional.filter(predicate);
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        return optional.map(mapper);
    }

    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        return optional.flatMap(mapper);
    }

    public T orElse(T other) {
        return optional.orElse(other);
    }

    public T orElseGet(Supplier<? extends T> other) {
        return optional.orElseGet(other);
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return optional.orElseThrow(exceptionSupplier);
    }

    public static <T1> DefaultOptional<T1> empty() {
        return new DefaultOptional<>(Optional.empty());
    }

    public static <T1> DefaultOptional<T1> empty(Class<T1> clazz) {
        return new DefaultOptional<>(Optional.empty());
    }

    public static <T1> DefaultOptional<T1> of(T1 value) {
        return new DefaultOptional<>(Optional.of(value));
    }

    public static <T1> DefaultOptional<T1> ofNullable(T1 value) {
        return new DefaultOptional<>(Optional.ofNullable(value));
    }
}
