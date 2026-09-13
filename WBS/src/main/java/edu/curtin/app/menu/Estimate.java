package edu.curtin.app.menu;

import edu.curtin.app.task.WBS;
import edu.curtin.app.User;
import edu.curtin.app.Util;

import java.util.*;

// Concrete strategy.
public class Estimate implements Menu
{
    private List<Integer> estimates = new ArrayList<>();

    @Override
    public void option(WBS wbs)
    {
        String id = User.getId();
        Util.check(!wbs.hasTask(id), "The task does not exist.");

        int estimators = Default.getEstimators();
        System.out.println("There are %d estimators.".formatted(estimators));
        for (int i = 0; i < estimators; i++)
        {
            estimates.add(User.getEstimate());
        }

        int effort = 0;
        // If the effort estimates are the same.
        if (estimates.stream().distinct().count() == 1)
        {
            effort = estimates.getFirst();
        }
        // If the effort estimates are different.
        else
        {
            switch(Default.getApproach())
            {
                case 1: effort = highest();
                    break;
                case 2: effort = median();
                    break;
                case 3: effort = revised();
                    break;
                default: System.out.println("Invalid reconciliation approach.");
            }
        }

        // If the effort estimate is known.
        if (effort > 0)
        {
            wbs.update(id, effort);
        }
    }

    private int highest()
    {
        return Collections.max(estimates);
    }

    private int median()
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

    private int revised()
    {
        return User.getRevised();
    }
}
