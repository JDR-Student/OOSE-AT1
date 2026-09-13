package edu.curtin.app.submenu;

// Concrete strategy.
public class Default implements SubMenu
{
    @Override
    public int option()
    {
        System.out.println("Invalid reconciliation approach.");
        // Reset to the current reconciliation approach.
        return 0;
    }
}
