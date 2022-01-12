package com.gof.behavioural.command.homeremote;

//Command class

public class LightOnCommand implements Command{

    Light light;
    // The constructor is passed the light it
    // is going to control.
    public LightOnCommand(Light light)
    {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
