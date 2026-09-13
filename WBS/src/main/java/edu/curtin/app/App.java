/*
 File:          App.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       To display the WBS and the menu.
 Comments:      None.
 Requires:      Utilises sub-menu and WBS.
 Reference:     None.

 Created:       31/08/2026
 Last Modified: TODO
*/

package edu.curtin.app;

import edu.curtin.app.submenu.*;
import edu.curtin.app.task.WBS;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class App
{
    // TODO private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args)
    {
        try
        {
            Util.check(args.length != 1, "A text file must be entered as an argument.");
            String file = args[0];

            WBS wbs = new WBS();
            IO.read(file, wbs);
            menu(wbs);
            IO.write(file, wbs);
        }
        catch (IOException | ParseFileException | IllegalArgumentException exception)
        {
            System.out.println(exception.getMessage());
        }

        User.close();
    }

    private static void menu(WBS wbs)
    {
        // Loop through the menu.
        int option = 0;
        do
        {
            try
            {
                wbs.display();

                option = User.requestOption();
                switch(option)
                {
                    // Estimate effort.
                    case 1: estimate(wbs);
                        break;
                    // Configure.
                    case 2:
                        Default.setEstimators(User.requestEstimators());
                        Default.setApproach(User.requestApproach());
                        break;
                    // Quit.
                    case 3: System.out.println("Exiting...");
                        break;
                    // Invalid menu option.
                    default: System.out.println("Invalid menu option.");
                }
            }
            catch (IllegalArgumentException exception)
            {
                System.out.println(exception.getMessage());
            }
        } while (option != 3);
    }

    private static void estimate(WBS wbs)
    {
        String id = User.requestId();
        Util.check(!wbs.hasTask(id), "The task does not exist.");

        List<Integer> estimates = User.requestEstimates(Default.getEstimators());

        int effort;
        // If the effort estimates are the same.
        if (estimates.stream().distinct().count() == 1)
        {
            effort = estimates.getFirst();
        }
        // If the effort estimates are different.
        else
        {
            SubMenu submenu;
            switch(Default.getApproach())
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
            effort = submenu.option();
        }

        wbs.update(id, effort);
    }
}
