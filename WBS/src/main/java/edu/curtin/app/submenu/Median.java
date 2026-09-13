/*
 File:          Median.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       A concrete strategy for the strategy pattern.
 Comments:      None.
 Requires:      None.
 Reference:     None.

 Created:       13/09/2026
 Last Modified: 13/09/2026
*/

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
