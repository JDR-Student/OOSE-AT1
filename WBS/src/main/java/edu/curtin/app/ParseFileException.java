/*
 File:          ParseFileExeception.java
 Author:        Jack Dylan Rendle
 Unit:          COMP2003

 Purpose:       To provide a custom exception when parsing a text file.
 Comments:      None.
 Requires:      None.
 Reference:     None.

 Created:       13/09/2026
 Last Modified: 13/09/2026
*/

package edu.curtin.app;

public class ParseFileException extends Exception
{
    public ParseFileException(String message)
    {
        super(message);
    }

    public ParseFileException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
