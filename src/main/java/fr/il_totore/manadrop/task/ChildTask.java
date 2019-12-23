package fr.il_totore.manadrop.task;

import org.gradle.api.Task;

public interface ChildTask {

    void run(Task parent);
}
