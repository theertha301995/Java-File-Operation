package java_test_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class Employee extends Thread {

    String name;
    Double Salary;
    int age;

    public String getEmpName() {
        return name;
    }

    public Double getSalary() {
        return Salary;
    }

    public int getAge() {
        return age;
    }

    public void setEmpName(String name) {
        this.name = name;
    }

    public void setSalary(Double salary) {
        this.Salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] names = {"Anu", "Neenu", "Akshay", "Arun", "Minnu", "Rohith", "Anna", "Priya", "Sara", "Reenu"};
        String name = names[random.nextInt(names.length)];
        double salary = 200000 + (800000 - 200000) * random.nextDouble();
        int age = 20 + random.nextInt(41);
        setEmpName(name);
        setSalary(salary);
        setAge(age);
    }
}

public class EmployeeData {

    public static void main(String[] args) throws InterruptedException {
        Employee[] employees = new Employee[10];
        Thread[] threads = new Thread[employees.length];

        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee();
            threads[i] = new Thread(employees[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        Arrays.sort(employees, (e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));

        for (Employee employee : employees) {
            System.out.println("Name: " + employee.getEmpName() + ", Salary: " + employee.getSalary().intValue() + ", Age: " + employee.getAge());
        }
        System.out.println("------------------------------------------------------");

        Arrays.sort(employees, (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        for (Employee employee : employees) {
            System.out.println("Name: " + employee.getEmpName() + ", Salary: " + employee.getSalary().intValue() + ", Age: " + employee.getAge());
        }
    }
}