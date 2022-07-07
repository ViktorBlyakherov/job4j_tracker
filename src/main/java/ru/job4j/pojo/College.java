package ru.job4j.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student vasya = new Student();
        vasya.setName("Vasilii Petrov");
        vasya.setGroup(20);
        vasya.setAdmission(new Date());
        System.out.println(vasya.getName());
        System.out.println(vasya.getGroup());
        System.out.println(vasya.getAdmission());
    }
}
