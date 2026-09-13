package edu.curtin.app;

import java.util.*;

public class User
{
    private static Scanner input = new Scanner(System.in);

    public static int getOption()
    {
        System.out.println("\n> 1. Estimate effort.");
        System.out.println("> 2. Configure.");
        System.out.println("> 3. Quit.");
        System.out.print("Please enter a menu option: ");
        return Util.parseInt(input.nextLine());
    }

    public static String getId()
    {
        System.out.print("Please enter a task id: ");
        return input.nextLine();
    }

    public static int getEstimate()
    {
        System.out.print("Please enter an estimate: ");
        return Util.parseInt(input.nextLine());
    }

    public static int getEstimators()
    {
        System.out.print("Please enter the number of estimators: ");
        return Util.parseInt(input.nextLine());
    }

    public static int getApproach()
    {
        System.out.println("\n> 1. Highest estimate.");
        System.out.println("> 2. Median estimate.");
        System.out.println("> 3. Revised estimate.");
        System.out.print("Please choose a reconciliation approach: ");
        return Util.parseInt(input.nextLine());
    }

    public static int getRevised()
    {
        System.out.print("Please enter a revised estimate: ");
        return Util.parseInt(input.nextLine());
    }

    public static void close()
    {
        input.close();
    }
}
