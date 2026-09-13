package edu.curtin.app.menu;

public class Default
{
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
