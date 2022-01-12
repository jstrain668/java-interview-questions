package com.solid.dip;

enum Relationship
{
    PARENT,
    CHILD,
    SIBLING
}

class Person
{
    public String name;
    // dob etc.


    public Person(String name) {
        this.name = name;
    }
}
