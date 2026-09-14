/*
 File:          App.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       To display the WBS and the menu.
 Comments:      None.
 Requires:      Utilises user, util, and WBS.
 Reference:     None.

 Created:       31/08/2026
 Last Modified: 14/09/2026
*/

package edu.curtin.app;

import edu.curtin.app.task.WBS;

import java.io.*;

public class App
{
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
            Util.logger.warning(() -> exception.getMessage());
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
                    case 1: wbs.updateEffort(User.requestId());
                        break;
                    // Configure.
                    case 2:
                        User.requestEstimators();
                        User.requestApproach();
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
                Util.logger.warning(() -> exception.getMessage());
                System.out.println(exception.getMessage());
            }
        } while (option != 3);
    }
}
