package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeJournal {
    private final List<Employee> employees;
    private final Map<String, List<String>> surnamesPerPosition;
    private final Map<String, List<Employee>> employeePerSalary;
    private final Set<Employee> uniqueSurnames;

    public EmployeeJournal(){
        employees = new ArrayList<>();
        surnamesPerPosition = new HashMap<>();
        employeePerSalary = new HashMap<>();
        uniqueSurnames = new HashSet<>();
    }

    public void fillList(List<String> input){
        for(String line : input) {
            String[] words = line.split("[^0-9a-zA-Z\\p{L}+]+");
            try {
                Employee employee = new Employee(words[0], words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
                Employee.addPosition(words[1]);
                employees.add(employee);
            } catch (NumberFormatException ex){
                System.out.println(ex);
            }
        }
    }

    public void firstTask() {
        List<List<Employee>> oldestYoungestEmployees = new ArrayList<>();
        for(String position : Employee.getAllPositions()){
            List<Employee> employeesPerPosition = employees.stream()
                    .filter(e -> e.getPosition().equals(position))
                    .collect(Collectors.toList());
            oldestYoungestEmployees.add(getOldestYoungestByPosition(employeesPerPosition));
            List<String> surnames = new ArrayList<>();
            for(Employee employee : employeesPerPosition){
                surnames.add(employee.getSurname());
            }
            surnamesPerPosition.put(position, surnames);
        }

        int i = 0;
        for(String position : surnamesPerPosition.keySet()){
            System.out.println(position + ": ");
            for(String surname : surnamesPerPosition.get(position)){
                System.out.println("\t" + surname);
            }
            System.out.println("Youngest: " + oldestYoungestEmployees.get(i).get(1));
            System.out.println("Oldest: " + oldestYoungestEmployees.get(i).get(0));
            i++;
        }

        System.out.println();
    }

    private List<Employee> getOldestYoungestByPosition(List<Employee> employeesPerPosition){
        List<Employee> sortedEmployees = employeesPerPosition.stream()
                .sorted(new Employee.YearComparator())
                .collect(Collectors.toList());

        List<Employee> result = new ArrayList<>();
        result.add(sortedEmployees.get(0));
        if(sortedEmployees.size() > 1) {
            result.add(sortedEmployees.get(sortedEmployees.size() - 1));
        }

        return result;
    }

    public void secondTask() {
        List<Employee> sortedEmployees = employees.stream()
                .sorted(new Employee.SalaryComparator())
                .collect(Collectors.toList());

        int lowestSalary = sortedEmployees.get(0).getSalary();
        int highestSalary = sortedEmployees.get(sortedEmployees.size() - 1).getSalary();

        employeePerSalary.put(Salary.LOW.label, employees.stream().
                filter(e -> e.getSalary() >= lowestSalary && e.getSalary() <= lowestSalary + 100).
                collect(Collectors.toList()));
        employeePerSalary.put(Salary.MEDIUM.label, employees.stream().
                filter(e -> e.getSalary() > lowestSalary + 100  && e.getSalary() < highestSalary - 100).
                collect(Collectors.toList()));
        employeePerSalary.put(Salary.HIGH.label, employees.stream().
                filter(e -> e.getSalary() >= highestSalary - 100 && e.getSalary() <= highestSalary).
                collect(Collectors.toList()));

        for(String salary : employeePerSalary.keySet()){
            System.out.println(salary + ": ");
            for (Employee employee : employeePerSalary.get(salary))
                System.out.println("\t" + employee);
        }

        System.out.println();
    }

    private enum Salary{
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High");

        public final String label;

        Salary(String label) {
            this.label = label;
        }
    }

    public void thirdTask(){
        List<Employee> secondEmployees = fillSecondList();
        uniqueSurnames.addAll(employees);
        uniqueSurnames.addAll(secondEmployees);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the year: ");
        int year = scanner.nextInt();
        scanner.close();
        uniqueSurnames.removeIf(employee -> employee.getYearOfBirth() < year);

        System.out.println("Collection after removing employees who are younger than the user input:");
        for (Employee employee : uniqueSurnames){
            System.out.println("\t" + employee);
        }

    }

    private List<Employee> fillSecondList(){
        List<Employee> secondList = new ArrayList<>();
        List<String> input = FileReader.readFromFile("employees_second.txt");
        for(String line : input) {
            String[] words = line.split("[^0-9a-zA-Z\\p{L}+]+");
            try {
                Employee employee = new Employee(words[0], words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
                secondList.add(employee);
            } catch (NumberFormatException ex){
                System.out.println(ex);
            }
        }
        return secondList;
    }

}
