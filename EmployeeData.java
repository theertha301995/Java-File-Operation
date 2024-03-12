package java_test_1;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmployeeData {

	public static Employee[] employeeDetails() {
		Employee[] employees = new Employee[10];
		Random random = new Random();
		for (int i = 0; i < employees.length; i++) {
			Employee employee = new Employee();
			String[] names = { "Anu", "Neenu", "Akshay", "Arun", "Minnu", "Rohith", "Anna", "Priya", "Sara", "Reenu" };
			String name = names[random.nextInt(names.length)];
			double salary = 200000 + (800000 - 200000) * random.nextDouble();
			int age = 20 + random.nextInt(41);
			employee.setEmpName(name);
			employee.setSalary(salary);
			employee.setAge(age);
			employees[i] = employee;
		}
		return employees;
	}

	public static void ageSort(Employee[] employees) {
		Arrays.sort(employees, Comparator.comparingInt(Employee::getAge));

	}

	static void salarySort(Employee[] employees) {
		Arrays.sort(employees, Comparator.comparingDouble(Employee::getSalary));
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Future<Employee[]> future = executorService.submit(new Callable<Employee[]>() {
            public Employee[] call() throws Exception {
                Employee[] employees = EmployeeData.employeeDetails();
                EmployeeData.ageSort(employees);
                EmployeeData.salarySort(employees);
                return employees;
            }
        });
		try
		{
			Employee[] AgeSorted = future.get();
			Employee[] SalarySorted=future.get();
			printEmployee p1= new printEmployee();
			p1.display(AgeSorted);
			System.out.println("---------------------");
			p1.display(SalarySorted);
		}
		catch(InterruptedException |ExecutionException e)
		{
			e.printStackTrace();
		}
	}

}
