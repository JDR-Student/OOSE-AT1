package edu.curtin.app.utility;

import edu.curtin.app.task.*;

import java.io.*;
import java.util.*;

public class Write
{
    public static void write(String file, Map<String, Task> tasks) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            for (Task task : tasks.values())
            {
                task.export(writer);
            }
        }
    }
}
