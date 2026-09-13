/*
 File:          Invalid.java
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

// Concrete strategy.
public class Invalid implements SubMenu
{
    @Override
    public int option()
    {
        System.out.println("Invalid reconciliation approach.");
        return 0; // Set the effort estimate as unknown.
    }
}
