package com.solid.dip;

import java.util.List;

interface RelationshipBrowser
{
    List<Person> findAllChildrenOf(String name);
}