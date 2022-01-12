package com.gof.behavioural.command.homeremote;

//Let's use a remote control as the example. Our remote is the center of home automation
//and can control everything. We'll just use a light as an example, that we can switch on
//or off, but we could add many more commands.
// A simple Java program to demonstrate
// implementation of Command Pattern using
// a remote control example.
// An interface for command

public interface Command
{
    public void execute();
}

