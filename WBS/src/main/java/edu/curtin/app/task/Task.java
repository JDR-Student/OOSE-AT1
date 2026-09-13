// The wayback machine must be used to view this page. https://www.geeksforgeeks.org/java/composite-design-pattern-in-java/

package edu.curtin.app.task;

import java.io.*;

// Component interface.
public interface Task
{
    // Update the effort estimate.
    void update(int effort);

    // Find a task.
    Task find(String id);

    // Sum the total effort estimate.
    int sumEffort();

    // Count any unknown effort estimates.
    int countUnknown();

    // Display.
    void display(String indent);

    default void display()
    {
        display("");
    }

    // Export.
    void export(BufferedWriter writer) throws IOException;
}
