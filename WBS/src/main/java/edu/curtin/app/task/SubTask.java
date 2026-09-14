/*
 File:          SubTask.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       The leaf class for the composite pattern.
 Comments:      None.
 Requires:      Utilises user, util, and sub-menu.
 Reference:     None.

 Created:       31/08/2026
 Last Modified: 14/09/2026
*/

package edu.curtin.app.task;

import edu.curtin.app.User;
import edu.curtin.app.Util;
import edu.curtin.app.submenu.*;

import java.io.*;
import java.util.*;

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

        Util.logger.info(() -> ("Updated estimated effort from %d to %d.".formatted(this.effort, effort)));
        this.effort = effort;
    }

    // If the effort estimate is known, then return true.
    private boolean isKnown()
    {
        return effort > 0;
    }

    @Override
    public boolean hasEffort()
    {
        return true; // As this is a task or sub-task.
    }

    // Update the effort estimate.
    @Override
    public void updateEffort()
    {
        List<Integer> estimates = User.requestEstimates(id);

        // If the effort estimates are the same.
        if (estimates.stream().distinct().count() == 1)
        {
            setEffort(estimates.getFirst());
        }
        // If the effort estimates are different.
        else
        {
            SubMenu submenu;
            switch(User.getApproach())
            {
                // Highest estimate.
                case 1: submenu = new Highest(estimates);
                    break;
                // Median estimate.
                case 2: submenu = new Median(estimates);
                    break;
                // A single revised estimate.
                case 3: submenu = new Revised();
                    break;
                // Invalid reconciliation approach.
                default: submenu = new Invalid();
            }
            setEffort(submenu.option());
        }
    }

    // Find a task.
    @Override
    public Task find(String id)
    {
        // If this is the task.
        return getId().equals(id) ? this : null;
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
