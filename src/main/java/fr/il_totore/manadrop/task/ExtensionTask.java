package fr.il_totore.manadrop.task;

public interface ExtensionTask<T> {

    void setExtension(T extension);

    T getExtension();
}
