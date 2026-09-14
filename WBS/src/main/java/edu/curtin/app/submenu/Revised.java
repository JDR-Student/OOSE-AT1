/*
 File:          Revised.java
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

import edu.curtin.app.User;

// Concrete strategy.
public class Revised implements SubMenu
{
    // Request a single revised effort estimate.
    @Override
    public int option()
    {
        return User.requestRevised();
    }
}
