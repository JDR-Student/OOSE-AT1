/*
 File:          User.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       To request user input.
 Comments:      None.
 Requires:      Utilises util.
 Reference:     None.

 Created:       12/09/2026
 Last Modified: 14/09/2026
*/

package edu.curtin.app;

import java.util.*;

public class User
{
    private static Scanner input = new Scanner(System.in);

    // Defaults.
    private static int estimators = 3;
    private static int approach = 3;

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
        System.out.print("Please enter an estimate: ");
        int estimate = Util.parseInt(input.nextLine());

        // If the effort estimate is negative.
        Util.check(estimate < 0, "The effort estimate must be a positive integer.");

        return estimate;
    }

    public static List<Integer> requestEstimates(String id)
    {
        System.out.println("\nFor task '%s':".formatted(id));

        List<Integer> estimates = new ArrayList<>();
        for (int i = 0; i < estimators; i++)
        {
            estimates.add(requestEstimate());
        }

        return estimates;
    }

    public static void requestEstimators()
    {
        System.out.print("Please enter the number of estimators: ");
        int estimators = Util.parseInt(input.nextLine());

        // If the number of estimators is negative.
        Util.check(estimators < 0, "The number of estimators must be a positive integer.");

        setEstimators(estimators);
        Util.logger.info(() -> ("Updated the number of estimators to %d.".formatted(estimators)));
    }

    // Sub-menu options.
    public static void requestApproach()
    {
        System.out.println("\n> 1. Highest estimate.");
        System.out.println("> 2. Median estimate.");
        System.out.println("> 3. Revised estimate.");
        System.out.print("Please choose a reconciliation approach: ");

        setApproach(Util.parseInt(input.nextLine()));
        Util.logger.info(() -> ("Updated the reconciliation approach to %d.".formatted(approach)));
    }

    public static int requestRevised()
    {
        System.out.print("Please enter a single revised estimate: ");
        int estimate = Util.parseInt(input.nextLine());

        // If the effort estimate is negative.
        Util.check(estimate < 0, "The effort estimate must be a positive integer.");

        return estimate;
    }

    public static void close()
    {
        input.close();
    }
}
