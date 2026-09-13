/*
 File:          Highest.java
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
public class Highest implements SubMenu
{
    private List<Integer> estimates;

    public Highest(List<Integer> estimates)
    {
        this.estimates = estimates;
    }

    // Get the highest effort estimate.
    @Override
    public int option()
    {
        // Get the highest effort estimate.
        return Collections.max(estimates);
    }
}
