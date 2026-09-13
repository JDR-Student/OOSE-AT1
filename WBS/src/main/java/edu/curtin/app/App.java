package edu.curtin.app;

import edu.curtin.app.task.WBS;
import edu.curtin.app.menu.*;

import java.io.*;
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
        catch (IOException | IllegalStateException exception)
        {
            System.out.println(exception.getMessage());
        }

        User.close();
    }

    private static void menu(WBS wbs)
    {
        Menu menu;
        int option = 0;

        // Loop through the menu.
        do
        {
            try
            {
                wbs.display();

                option = User.getOption();
                switch(option)
                {
                    // Estimate effort.
                    case 1:
                        menu = new Estimate();
                        menu.option(wbs);
                        break;
                    // Configure.
                    case 2:
                        menu = new Configure();
                        menu.option(wbs);
                        break;
                    // Quit.
                    case 3:
                        System.out.println("Exiting...");
                        break;
                    // If the menu option is invalid.
                    default: System.out.println("Invalid menu option.");
                }
            }
            catch (IllegalStateException | IllegalArgumentException exception)
            {
                System.out.println(exception.getMessage());
            }
        } while (option != 3);
    }
}
