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
		Future<Employee[]> ageSortedFuture = executorService.submit(new Callable<Employee[]>() {
	        public Employee[] call() throws Exception {
	            Employee[] employees = EmployeeData.employeeDetails();
	            EmployeeData.ageSort(employees);
	            return employees;
	        }
	    });

	    Future<Employee[]> salarySortedFuture = executorService.submit(new Callable<Employee[]>() {
	        public Employee[] call() throws Exception {
	            Employee[] employees = EmployeeData.employeeDetails();
	            EmployeeData.salarySort(employees);
	            return employees;
	        }
	    });

	    try {
	        Employee[] ageSorted = ageSortedFuture.get();
	        Employee[] salarySorted = salarySortedFuture.get();

	        printEmployee printer = new printEmployee();

	        System.out.println("Age Sorted:");
	        printer.display(ageSorted);

	        System.out.println("---------------------");

	        System.out.println("Salary Sorted:");
	        printer.display(salarySorted);
	    } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
	    }

	    executorService.shutdown();
	}
}