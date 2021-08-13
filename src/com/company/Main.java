package com.company;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
    List<Person> persons = getPeople();
        //***************************** Imperative approach ***********************************
/*
	List<Person> females = new ArrayList<>();
	for(Person person: persons){
	    if(person.getGender().equals(Gender.MALE)){
	        females.add(person);
        }
    }
	females.forEach(System.out::println);
    }
*/
        //**************************** Declarative approach ***********************************
    List<Person> females = persons.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .collect(Collectors.toList());
   females.forEach(System.out::println);
        System.out.println("**************************************************************");

    List<Person> sortedByAge = persons.stream()
            .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
            .collect(Collectors.toList());
    sortedByAge.forEach(System.out::println);
        System.out.println("**************************************************************");

    Boolean allMatchByAge = persons.stream()
            .allMatch(person -> person.getAge() > 25 );
    System.out.println(allMatchByAge);
    System.out.println("**************************************************************");


    Boolean anyMatchbyAge = persons.stream()
            .anyMatch(person -> person.getAge()>10);
    System.out.println(anyMatchbyAge);
        System.out.println("**************************************************************");

    Boolean nonMatchByName = persons.stream()
            .noneMatch(person -> person.getName().equals("adesanya"));
        System.out.println(nonMatchByName);
        System.out.println("**************************************************************");

    persons.stream()
            .max(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);

    persons.stream()
            .min(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println);

    Map<Gender,List<Person>> groupByGender =
            persons.stream().collect(Collectors.groupingBy(Person::getGender));

    groupByGender.forEach((gender, people) -> {
        System.out.println(gender);
        people.forEach(System.out::println);

    });

    Optional<String> oldestFemaleName = persons.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .max(Comparator.comparing(Person::getAge))
            .map(Person::getName);
        oldestFemaleName.ifPresent(System.out::println);
    }


    private static List<Person> getPeople() {
        return List.of(
                new Person("driss", 22, Gender.MALE),
                new Person("omar", 29, Gender.MALE),
                new Person("fouad", 27, Gender.MALE),
                new Person("ali", 24, Gender.MALE),
                new Person("othman", 26, Gender.MALE),
                new Person("habiba", 58, Gender.FEMALE),
                new Person("Mouna", 23, Gender.FEMALE),
                new Person("ghita", 24, Gender.FEMALE)
        );
    }
}

