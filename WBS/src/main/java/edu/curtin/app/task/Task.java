package edu.curtin.app.task;

import java.io.*;

// Component interface.
public interface Task
{
    // Find a super-task.
    Task find(String id);

    // Display.
    void display(String indent);

    default void display()
    {
        display("");
    }

    // Export.
    void export(BufferedWriter writer) throws IOException;
}
