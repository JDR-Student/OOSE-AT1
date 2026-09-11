package edu.curtin.app.utility;

import edu.curtin.app.task.*;

import java.io.*;
import java.util.*;

public class Read
{
    public static void read(String file, Map<String, Task> tasks) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line = reader.readLine();

            // If the first line of the first is empty.
            check(line == null, "The file is empty.");

            do
            {
                String[] parts = line.split("\\s*;\\s*", -1);

                String superId = parts[0];
                String id = parts[1];
                String description = parts[2];

                switch (parts.length)
                {
                    case 3:
                        // Super-task.
                        if (superId.isEmpty())
                        {
                            tasks.put(id, new SuperTask(id, description));
                        }
                        // Super-task as a sub-task.
                        else
                        {
                            // Find the super-task and add the sub-task.
                            add(tasks, superId, id, new SuperTask(id, description));
                        }
                        break;
                    case 4:
                        int effort = parseInt(parts[3]);

                        // Task.
                        if (superId.isEmpty())
                        {
                            tasks.put(id, new SubTask(id, description, effort));
                        }
                        // Sub-task.
                        else
                        {
                            // Find the super-task and add the sub-task.
                            add(tasks, superId, id, new SubTask(id, description, effort));
                        }
                        break;
                    default: throw new IllegalStateException("Each line must contain between two and four parts.");
                }
            } while ((line = reader.readLine()) != null);
        }
    }

    private static void add(Map<String, Task> tasks, String superId, String subId, Task subtask)
    {
        boolean found = false;

        for (Task supertask : tasks.values())
        {
            // Find the super-task.
            supertask = supertask.find(superId);

            // If found, then add the sub-task.
            if (supertask != null)
            {
                found = true;
                ((SuperTask)supertask).add(subId, subtask);
            }
        }

        // If the super-task does not exist.
        check(!found, "Unable to find super-task '%s' to add sub-task '%s'.".formatted(superId, subId));
    }

    private static int parseInt(String effort)
    {
        // If the effort estimate is unknown.
        if (effort.isEmpty())
        {
            return 0;
        }
        // If the effort estimate is known.
        else
        {
            try
            {
                return Integer.parseInt(effort);
            }
            catch (NumberFormatException exception)
            {
                throw new IllegalStateException("Unable to parse '%s' as an integer.".formatted(effort));
            }
        }
    }

    private static void check(boolean condition, String message)
    {
        if (condition)
        {
            throw new IllegalStateException(message);
        }
    }
}
