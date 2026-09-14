/*
 File:          Task.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       The component interface for the composite pattern.
 Comments:      None.
 Requires:      None.
 Reference:     None.

 Created:       31/08/2026
 Last Modified: 14/09/2026
*/

package edu.curtin.app.task;

import java.io.*;

// Component interface.
public interface Task
{
    // Check whether the task has effort estimate(s).
    boolean hasEffort();

    // Update the effort estimate.
    void updateEffort();

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
