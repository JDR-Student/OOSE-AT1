package edu.curtin.app.task;

import edu.curtin.app.Util;

import java.io.*;
import java.util.*;

// Work Breakdown Structure.
public class WBS
{
    public Map<String, Task> tasks;

    public WBS()
    {
        // Sorted via key.
        tasks = new TreeMap<>();
    }

    public void add(String root, String id, String description)
    {
        // Super-task.
        if (root.isEmpty())
        {
            tasks.put(id, new SuperTask(id, description));
        }
        // Super-task as a sub-task.
        else
        {
            // Find the super-task and add the sub-task.
            put(root, id, new SuperTask(id, description));
        }
    }

    public void add(String root, String id, String description, int effort)
    {
        // Task.
        if (root.isEmpty())
        {
            tasks.put(id, new SubTask(id, description, effort));
        }
        // Task as a sub-task.
        else
        {
            // Find the super-task and add the sub-task.
            put(root, id, new SubTask(id, description, effort));
        }
    }

    public void put(String root, String id, Task task)
    {
        Task supertask = find(root);

        // If the super-task does not exist.
        Util.check(supertask == null, "Unable to find super-task '%s' to add sub-task '%s'.".formatted(root, id));

        // If found, then add the sub-task.
        ((SuperTask)supertask).add(id, task);
    }

    // Update the effort estimate of a task or sub-task.
    public void update(String id, int effort)
    {
        Task task = find(id);

        // If the task does not exist.
        Util.check(task == null, "Unable to find task '%s' to update the effort estimate(s).".formatted(id));

        // If found, then update the effort estimate.
        task.update(effort);
    }

    // Check whether the task exists.
    public boolean hasTask(String id)
    {
        Task task = find(id);

        // If the task exists.
        if (task != null)
        {
            return true;
        }

        return false;
    }

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
