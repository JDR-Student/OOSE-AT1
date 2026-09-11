package edu.curtin.app;

import edu.curtin.app.task.*;
import edu.curtin.app.utility.*;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class App
{
    private static final Logger logger = Logger.getLogger(App.class.getName());

    // Work Breakdown Structure (WBS) sorted via key.
    private static Map<String, Task> tasks = new TreeMap<>();

    public static void main(String[] args)
    {
        // TODO replace with user input
        String file = "data.txt";

        try
        {
            Read.read(file, tasks);

            tasks.forEach((id, task) -> task.display());

            Write.write(file, tasks);

        }
        catch (IOException | IllegalStateException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
