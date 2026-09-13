package edu.curtin.app;

import java.util.*;

public class User
{
    private static Scanner input = new Scanner(System.in);
    private static int estimators;
    private static int approach;

    // Getters.
    public static int getEstimators()
    {
        return estimators;
    }

    public static int getApproach()
    {
        return approach;
    }

    // Setters.
    public static void setEstimators(int estimators)
    {
        User.estimators = estimators;
    }

    public static void setApproach(int approach)
    {
        User.approach = approach;
    }

    // Menu options.
    public static int requestOption()
    {
        System.out.println("\n> 1. Estimate effort.");
        System.out.println("> 2. Configure.");
        System.out.println("> 3. Quit.");
        System.out.print("Please enter a menu option: ");
        return Util.parseInt(input.nextLine());
    }

    public static String requestId()
    {
        System.out.print("Please enter a task id: ");
        return input.nextLine();
    }

    private static int requestEstimate()
    {
        int estimate = -1;
        do
        {
            System.out.print("Please enter an estimate: ");
            estimate = Util.parseInt(input.nextLine());
        } while (estimate < 0);
        return estimate;
    }

    public static List<Integer> requestEstimates()
    {
        System.out.println("There are %d estimators.".formatted(estimators));

        List<Integer> estimates = new ArrayList<>();
        for (int i = 0; i < estimators; i++)
        {
            estimates.add(requestEstimate());
        }
        return estimates;
    }

    public static int requestEstimators()
    {
        int estimators = -1;
        do
        {
            System.out.print("Please enter the number of estimators: ");
            estimators = Util.parseInt(input.nextLine());
        } while (estimators < 0);
        return estimators;
    }

    // Sub-menu options.
    public static int requestApproach()
    {
        System.out.println("\n> 1. Highest estimate.");
        System.out.println("> 2. Median estimate.");
        System.out.println("> 3. Revised estimate.");
        System.out.print("Please choose a reconciliation approach: ");
        return Util.parseInt(input.nextLine());
    }

    public static int requestRevised()
    {
        System.out.print("Please enter a revised estimate: ");
        return Util.parseInt(input.nextLine());
    }

    public static void close()
    {
        input.close();
    }
}
