package edu.curtin.app;

public class Util
{
    public static void check(boolean condition, String message)
    {
        if (condition)
        {
            throw new IllegalStateException(message);
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
            throw new IllegalArgumentException("Unable to parse '%s' as an integer.".formatted(field));
        }
    }
}
