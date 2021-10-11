package com.company;

public class Main {

    public static void main(String[] args) {
        EmployeeJournal employeeJournal = new EmployeeJournal();
        employeeJournal.fillList(FileReader.readFromFile("employees.txt"));
        employeeJournal.firstTask();
        employeeJournal.secondTask();
        employeeJournal.thirdTask();
    }
}
