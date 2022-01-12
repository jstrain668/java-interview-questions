package com.gof.behavioural.command.homeremote;


// Invoker - A Simple remote control with one button

public class SimpleRemoteControl {

    Command button; // only one button
    public SimpleRemoteControl()
    {
    }

    public void setCommand(Command command)
    {
        // set the command the remote will execute
        button = command;
    }

    public void buttonWasPressed()
    {
        button.execute();
    }

}
