/*
 File:          WBS.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       The context for the composite pattern.
 Comments:      None.
 Requires:      Utilises util.
 Reference:     None.

 Created:       11/09/2026
 Last Modified: 14/09/2026
*/

package edu.curtin.app.task;

import edu.curtin.app.Util;

import java.io.*;
import java.util.*;

// Work Breakdown Structure.
public class WBS
{
    private Map<String, Task> tasks;

    public WBS()
    {
        // Sorted via key.
        tasks = new TreeMap<>();
    }

    private void put(String root, String id, Task task)
    {
        Task supertask = find(root);

        // If the super-task does not exist.
        Util.check(supertask == null, "Unable to find super-task '%s' to add sub-task '%s'.".formatted(root, id));

        // If found, then add the sub-task.
        ((SuperTask)supertask).add(id, task);
    }

    public void add(String root, String id, String description)
    {
        // Super-task.
        if (root.isEmpty())
        {
            tasks.put(id, new SuperTask(id, description));
            Util.logger.info(() -> ("Added task '%s' as a super-task.".formatted(id)));
        }
        // Super-task as a sub-task.
        else
        {
            // Find the super-task and add the sub-task.
            put(root, id, new SuperTask(id, description));
            Util.logger.info(() -> ("Added super-task '%s' as a sub-task.".formatted(id)));
        }
    }

    public void add(String root, String id, String description, int effort)
    {
        // Task.
        if (root.isEmpty())
        {
            tasks.put(id, new SubTask(id, description, effort));
            Util.logger.info(() -> ("Added task '%s'.".formatted(id)));
        }
        // Task as a sub-task.
        else
        {
            // Find the super-task and add the sub-task.
            put(root, id, new SubTask(id, description, effort));
            Util.logger.info(() -> ("Added task '%s' as a sub-task.".formatted(id)));
        }
    }

    // Update the effort estimate of a task or sub-task.
    public void updateEffort(String id)
    {
        Task task = find(id);

        // If the task does not exist.
        Util.check(task == null, "Task '%s' does not exist.".formatted(id));
        // If the task does not have effort estimate(s).
        Util.check(!task.hasEffort(), "Task '%s' does not have effort estimate(s).".formatted(id));

        // If found, then update the effort estimate(s).
        task.updateEffort();
    }

    // Recursion is required to find a task that may be a sub-task.
    @SuppressWarnings({"PMD.AvoidReassigningLoopVariables"})
    // Find a task.
    public Task find(String id)
    {
        for (Task task : tasks.values())
        {
            // Recursively find the task.
            task = task.find(id);

            // If found.
            if (task != null)
            {
                Util.logger.info(() -> ("Found task '%s'.".formatted(id)));
                return task;
            }
        }
        return null;
    }

    public int sumEffort()
    {
        int effort = 0;
        for (Task task : tasks.values())
        {
            effort += task.sumEffort();
        }
        return effort;
    }

    public int countUnknown()
    {
        int count = 0;
        for (Task task : tasks.values())
        {
            count += task.countUnknown();
        }
        return count;
    }

    public void display()
    {
        System.out.println(); // New line.
        tasks.forEach((id, task) -> task.display());

        System.out.println("\nTotal known effort = %d".formatted(sumEffort()));
        System.out.println("Unknown tasks = %d".formatted(countUnknown()));
    }

    public void export(BufferedWriter writer) throws IOException
    {
        for (Task task : tasks.values())
        {
            task.export(writer);
        }
    }
}
