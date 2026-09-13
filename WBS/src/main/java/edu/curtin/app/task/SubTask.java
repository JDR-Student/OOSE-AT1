/*
 File:          SubTask.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       The leaf class for the composite pattern.
 Comments:      None.
 Requires:      Utilises util.
 Reference:     None.

 Created:       31/08/2026
 Last Modified: 13/09/2026
*/

package edu.curtin.app.task;

import edu.curtin.app.Util;

import java.io.*;

// Leaf class.
public class SubTask implements Task
{
    private String id;
    private String description;
    private int effort;

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
        Util.check(effort < 0, "The effort estimate must be a positive integer.");

        this.effort = effort;
    }

    // Update the effort estimate.
    @Override
    public void update(int effort)
    {
        setEffort(effort);
    }

    // If the effort estimate is known, then return true.
    private boolean isKnown()
    {
        return effort > 0;
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

        return null;
    }

    // Sum the total effort estimate.
    @Override
    public int sumEffort()
    {
        return effort;
    }

    // Count any unknown effort estimates.
    @Override
    public int countUnknown()
    {
        return isKnown() ? 0 : 1;
    }

    // Display.
    @Override
    public void display(String indent)
    {
        if (isKnown())
        {
            System.out.println("%s%s: %s, effort = %d".formatted(indent, id, description, effort));
        }
        else
        {
            System.out.println("%s%s: %s".formatted(indent, id, description));
        }
    }

    // Export.
    @Override
    public void export(BufferedWriter writer) throws IOException
    {
        if (isKnown())
        {
            writer.write("; %s ; %s ; %d".formatted(id, description, effort));
        }
        else
        {
            writer.write("; %s ; %s ;".formatted(id, description));
        }

        writer.newLine();
    }
}
