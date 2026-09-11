package edu.curtin.app.task;

import java.io.*;

// Leaf class.
public class SubTask implements Task
{
    private String id;
    private String description;
    private int effort;

    // TODO validation
    public SubTask(String id, String description, int effort)
    {
        this.id = id;
        this.description = description;
        this.effort = effort;
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

    public int getEffort()
    {
        return effort;
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

    public void setEffort(int effort) throws IllegalArgumentException
    {
        // If the effort estimate is negative.
        if (effort < 0)
        {
            throw new IllegalArgumentException("The effort estimate must be a positive integer.");
        }

        this.effort = effort;
    }

    // Find a super-task.
    @Override
    public Task find(String id)
    {
        return null; // As this is a sub-task.
    }

    // Display.
    @Override
    public void display(String indent)
    {
        // If the effort estimate is known.
        if (effort > 0)
        {
            System.out.println("%s%s: %s, effort = %d".formatted(indent, id, description, effort));
        }
        // If the effort estimate is unknown.
        else
        {
            System.out.println("%s%s: %s".formatted(indent, id, description));
        }
    }

    // Export.
    @Override
    public void export(BufferedWriter writer) throws IOException
    {
        // If the effort estimate is known.
        if (effort > 0)
        {
            writer.write("; %s ; %s ; %d".formatted(id, description, effort));
        }
        // If the effort estimate is unknown.
        else
        {
            writer.write("; %s ; %s ;".formatted(id, description));
        }

        writer.newLine();
    }
}
