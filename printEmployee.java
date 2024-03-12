package java_test_1;

import java.text.SimpleDateFormat;

public class printEmployee {
	 public void display(Employee[] employees)
	 {
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		 for (Employee employee : employees) {
				System.out.println(timeStamp+"Name: " + employee.getEmpName() + ", Salary: " + employee.getSalary().intValue()
						+ ", Age: " + employee.getAge());

	 }

}
 }
