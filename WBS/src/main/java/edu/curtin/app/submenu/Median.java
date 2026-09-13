package edu.curtin.app.submenu;

import java.util.*;

// Concrete strategy.
public class Median implements SubMenu
{
    private List<Integer> estimates;

    public Median(List<Integer> estimates)
    {
        this.estimates = estimates;
    }

    // Get the median effort estimate.
    @Override
    public int option()
    {
        // Sort the estimates in ascending order.
        Collections.sort(estimates);

        int size = estimates.size();
        // If the size is odd.
        if (size % 2 == 1)
        {
            return estimates.get(size / 2);
        }
        // If the size is even.
        else
        {
            return (estimates.get(size / 2) + estimates.get((size / 2) - 1)) / 2;
        }
    }
}
