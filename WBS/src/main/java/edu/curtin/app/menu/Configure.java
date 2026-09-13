package edu.curtin.app.menu;

import edu.curtin.app.task.WBS;
import edu.curtin.app.User;
import edu.curtin.app.Util;

// Concrete strategy.
public class Configure implements Menu
{
    @Override
    public void option(WBS wbs)
    {
        Default.setEstimators(User.getEstimators());

        // Loop while the reconciliation approach is not between 1 and 3 (inclusive).
        int approach = 0;
        do
        {
            approach = User.getApproach();
            Util.check((approach < 1 && approach > 3), "Invalid option.");
        } while (approach < 1 && approach > 3);
        Default.setApproach(approach);
    }
}
