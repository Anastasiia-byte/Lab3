package com.company;

import java.util.*;

public class Employee {

    private final String surname;
    private final String position;
    private final int yearOfBirth;
    private final int salary;
    private static Set<String> allPositions;

    public Employee(String surname, String position, int yearOfBirth, int salary){
        this.surname = surname;
        this.position = position;
        this.yearOfBirth = yearOfBirth;
        this.salary = salary;
    }

    public static void addPosition(String position){
        if (allPositions == null){
            allPositions = new HashSet<>();
        }
        allPositions.add(position);
    }

    public static Set<String> getAllPositions(){
        return allPositions;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getSurname().equals(employee.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurname());
    }

    public static class YearComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getYearOfBirth(), o2.getYearOfBirth());
        }
    }

    public static class SalaryComparator implements Comparator<Employee>{
        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getSalary(), o2.getSalary());
        }
    }


}
