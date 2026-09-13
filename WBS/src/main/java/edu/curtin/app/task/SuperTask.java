package edu.curtin.app.task;

import java.io.*;
import java.util.*;

// Composite class.
public class SuperTask implements Task
{
    private String id;
    private String description;
    private Map<String, Task> tasks;

    public SuperTask(String id, String description)
    {
        this.id = id;
        this.description = description;
        tasks = new TreeMap<>();
    }

    // Getters.
    public String getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    // Setters.
    public void setId(String id)
    {
        this.id = id;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    // Add a task or super-task.
    public void add(String id, Task task)
    {
        tasks.put(id, task);
    }

    // Update the effort estimate of a task or sub-task.
    @Override
    public void update(int effort)
    {
        for (Task task : tasks.values())
        {
            // Recursion.
            task.update(effort);
        }
    }

    // Find a task.
    @Override
    public Task find(String id)
    {
        // If this is the task.
        if (getId().equals(id))
        {
            return this;
        }
        else
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
        }

        return null;
    }

    // Sum the total effort estimate of this super-task and any sub-tasks.
    @Override
    public int sumEffort()
    {
        int estimate = 0;
        for (Task task : tasks.values())
        {
            estimate += task.sumEffort();
        }
        return estimate;
    }

    // Count any unknown effort estimates.
    @Override
    public int countUnknown()
    {
        int count = 0;
        for (Task task : tasks.values())
        {
            count += task.countUnknown();
        }
        return count;
    }

    // Display.
    @Override
    public void display(String indent)
    {
        System.out.println("%s%s: %s".formatted(indent, id, description));

        indent += "  ";
        for (Task task : tasks.values())
        {
            task.display(indent);
        }
    }

    // Export.
    @Override
    public void export(BufferedWriter writer) throws IOException
    {
        writer.write("; %s ; %s".formatted(id, description));
        writer.newLine();

        for (Task task : tasks.values())
        {
            writer.write("%s ".formatted(id));
            task.export(writer);
        }
    }
}
