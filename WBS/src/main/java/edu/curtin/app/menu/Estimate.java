package edu.curtin.app.menu;

import java.util.*;

import edu.curtin.app.User;
import edu.curtin.app.Util;
import edu.curtin.app.submenu.*;
import edu.curtin.app.submenu.Default;
import edu.curtin.app.task.WBS;

// Concrete strategy.
public class Estimate implements Menu
{
    private WBS wbs;

    public Estimate(WBS wbs)
    {
        this.wbs = wbs;
    }

    @Override
    public void option()
    {
        String id = User.requestId();
        Util.check(!wbs.hasTask(id), "The task does not exist.");

        List<Integer> estimates = User.requestEstimates();

        wbs.update(id, getEffort(estimates));
    }

    private int getEffort(List<Integer> estimates)
    {
        int effort = 0;

        // If the effort estimates are the same.
        if (estimates.stream().distinct().count() == 1)
        {
            effort = estimates.getFirst();
        }
        // If the effort estimates are different.
        else
        {
            SubMenu submenu;
            switch(User.getApproach())
            {
                case 1: submenu = new Highest(estimates);
                    break;
                case 2: submenu = new Median(estimates);
                    break;
                case 3: submenu = new Revised();
                    break;
                // Invalid reconciliation approach.
                default: submenu = new Default();
            }
            effort = submenu.option();
        }

        return effort;
    }
}
