package fr.il_totore.lavadrop.task;

import org.gradle.api.Task;

public interface ChildTask {

    void run(Task parent);
}
