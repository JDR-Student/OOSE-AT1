/*
 File:          Util.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       To provide convenient methods to check conditions and parse integers.
 Comments:      None.
 Requires:      None.
 Reference:     None.

 Created:       11/09/2026
 Last Modified: 14/09/2026
*/

package edu.curtin.app;

import java.util.logging.*;

public class Util
{
    public static final Logger logger = Logger.getLogger(App.class.getName());

    public static void check(boolean condition, String message)
    {
        if (condition)
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static int parseInt(String field)
    {
        try
        {
            return Integer.parseInt(field);
        }
        catch (NumberFormatException exception)
        {
            throw new IllegalArgumentException("Unable to parse '%s' as an integer.".formatted(field), exception);
        }
    }
}
