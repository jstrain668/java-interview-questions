package com.solid.ocp;

interface Specification<T> {
    boolean isSatisfied(T item);
}