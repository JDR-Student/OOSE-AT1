package edu.curtin.app;

import edu.curtin.app.task.WBS;

import java.io.*;

public class IO
{
    public static void read(String file, WBS wbs) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line = reader.readLine();

            // If the first line of the first is empty.
            Util.check(line == null, "The file is empty.");

            do
            {
                String[] parts = line.split("\\s*;\\s*", -1);

                String root = parts[0];
                String id = parts[1];
                String description = parts[2];

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
                    default: throw new IllegalStateException("Each line must contain between two and four parts.");
                }
            } while ((line = reader.readLine()) != null);
        }
    }

    public static void write(String file,  WBS wbs) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            wbs.export(writer);
        }
    }
}
