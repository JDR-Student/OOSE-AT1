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

    public void add(String id, Task task)
    {
        tasks.put(id, task);
    }

    // Find a super-task.
    @Override
    public Task find(String id)
    {
        // If this is the task.
        if (this.id.equals(id))
        {
            return this;
        }
        else
        {
            for (Task task : tasks.values())
            {
                // Recursion.
                task = task.find(id);

                // If the task is a sub-task.
                if (task != null)
                {
                    return task;
                }
            }
        }

        return null;
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

        // If there are sub-tasks.
        if (!tasks.isEmpty())
        {
            for (Task task : tasks.values())
            {
                writer.write("%s ".formatted(id));
                task.export(writer);
            }
        }
    }
}
