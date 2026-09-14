/*
 File:          IO.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       To read and write the WBS to a text file.
 Comments:      None.
 Requires:      Utilises util and WBS.
 Reference:     None.

 Created:       10/09/2026
 Last Modified: 14/09/2026
*/

package edu.curtin.app;

import edu.curtin.app.task.WBS;

import java.io.*;

public class IO
{
    public static void read(String file, WBS wbs) throws IOException, ParseFileException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line = reader.readLine();

            // If the first line of the file is empty.
            if (line == null)
            {
                throw new ParseFileException("The file '%s' is empty.".formatted(file));
            }

            do
            {
                String[] parts = line.split("\\s*;\\s*", -1);

                String root = parts[0];
                String id = parts[1];
                String description = parts[2];

                // If the task already exists.
                Util.check(wbs.find(id) != null, "Task '%s' already exists.".formatted(id));

                try
                {
                    switch (parts.length)
                    {
                        // Add a super-task or a super-task as a sub-task.
                        case 3: wbs.add(root, id, description);
                            break;
                        // Add a task or a task as a sub-task.
                        case 4:
                            int effort = 0;
                            // If the effort estimate is known.
                            if (!parts[3].isEmpty())
                            {
                                effort = Util.parseInt(parts[3]);
                            }
                            wbs.add(root, id, description, effort);
                            break;
                        default: throw new ParseFileException("Each line must contain between two and four parts.");
                    }
                }
                catch (ClassCastException exception)
                {
                    throw new ParseFileException("Task '%s' is not a super-task. Unable to add sub-task '%s'.".formatted(root, id), exception);
                }
            } while ((line = reader.readLine()) != null);
            Util.logger.info(() -> ("Read from file '%s'.".formatted(file)));
        }
    }

    public static void write(String file,  WBS wbs) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            wbs.export(writer);
            Util.logger.info(() -> ("Wrote to file '%s'.".formatted(file)));
        }
    }
}
