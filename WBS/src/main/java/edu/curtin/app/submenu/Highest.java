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
