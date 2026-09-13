package edu.curtin.app.submenu;

import edu.curtin.app.User;

// Concrete strategy.
public class Revised implements SubMenu
{
    // Get a single revised effort estimate.
    @Override
    public int option()
    {
        return User.requestRevised();
    }
}
