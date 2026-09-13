/*
 File:          Default.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       To store the number of estimators and the chosen reconciliation approach.
 Comments:      None.
 Requires:      None.
 Reference:     None.

 Created:       12/09/2026
 Last Modified: 13/09/2026.
*/

package edu.curtin.app;

public class Default
{
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
        Default.estimators = estimators;
    }

    public static void setApproach(int approach)
    {
        Default.approach = approach;
    }
}
