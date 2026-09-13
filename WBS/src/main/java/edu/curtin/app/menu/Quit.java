package edu.curtin.app.menu;

// Concrete strategy.
public class Quit implements Menu
{
    @Override
    public void option()
    {
        System.out.println("Exiting...");
    }
}
