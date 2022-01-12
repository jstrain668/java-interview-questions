package com.solid.ocp;

import java.util.List;

//Reference: https://medium.com/@kgy1559/solid-design-principle-open-closed-principle-specification-ocp-138f546fdba

public class SpecificationDemo {

    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree  = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        BetterFilter betterFilter = new BetterFilter();
        System.out.println("Green products : ");
        betterFilter.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(product -> System.out.println(" - " + product.name + " is green"));

        System.out.println("Large products : ");
        betterFilter.filter(products, new SizeSpecification(Size.LARGE))
                .forEach(product -> System.out.println(" - " + product.name + " is Large"));

        System.out.println("Large blue item : ");
        betterFilter.filter(products, new AndSpecification<>(new SizeSpecification(Size.LARGE), new ColorSpecification(Color.BLUE)))
                .forEach(product -> System.out.println(" - " + product.name + " is Large and Blue"));
    }
}
