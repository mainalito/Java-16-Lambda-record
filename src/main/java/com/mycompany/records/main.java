/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.records;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Charl3s
 */
public class main {

    public static List<Student> getStudent() {
        return List.of(
                new Student("Charles", 19, Gender.MALE),
                new Student("Jaymo", 18, Gender.MALE),
                new Student("Halima", 17, Gender.FEMALES),
                new Student("Fiona", 24, Gender.FEMALES),
                new Student("Jeddy", 23, Gender.FEMALES),
                new Student("Freddy", 15, Gender.MALE),
                new Student("Berseker", 60, Gender.MALE)
        );
    }

    public static void main(String[] args) {
        List<Student> student = getStudent();
        //lambda or declarative approach
        //filter
        System.out.println("====================FILTER========================");
        List<Student> females = student.stream().filter(Student -> Student.gender().equals(Gender.FEMALES))
                .collect(Collectors.toList());
        females.forEach(System.out::println);
        System.out.println("========================SORT=============================");
        //sort --> user .reversed()
        List<Student> sorted = student.stream().sorted(Comparator.comparing(Student::age)).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        //All match -> takes a boolean
        System.out.println("==========================ALLMATCH=============================");
        boolean Allmatch = student.stream().allMatch(Student -> Student.age() > 18);
        System.out.println(Allmatch);

        //anymatch ->mathces atleast one person
        System.out.println("=======================ANYMATCH================================");
        boolean anyMatch = student.stream().anyMatch(Student -> Student.age() > 28);
        System.out.println(anyMatch);

        //none match -> search for objects not available
        System.out.println("=========================NONEMATCH==============================");
        boolean noneMatch = student.stream().noneMatch(Student -> Student.name().equals("Chalo"));
        System.out.println(noneMatch);

        //max
        System.out.println("=========================MAX==============================");
        student.stream()
                .max(Comparator.comparing(Student::age))
                .ifPresent(Student -> {
                    System.out.println(Student);
                });
        //MIN
        System.out.println("====================MIN===================================");
        student.stream()
                .min(Comparator.comparing(Student::age))
                .ifPresent(Student -> {
                    System.out.println(Student);
                });
        //GROUP
        System.out.println("======================GROUP=================================");
        Map<Gender, List<Student>> collect = student.stream()
                .collect(Collectors.groupingBy(Student::gender));
        collect.forEach((gender, stud) -> {
            System.out.println(gender);
            stud.forEach(System.out::println);
            System.out.println();
        });
        System.out.println("==========================OLDESTFEMALE============================");
        Optional<String> oldestFemaleAge= student.stream()
//                .filter(Student -> Student.gender().equals(Gender.FEMALES))
                .max(Comparator.comparing(Student::age))
                .map(Student::name);
        oldestFemaleAge.ifPresent(System.out::println);
    }
}
