package ca.myseneca.test;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

import ca.myseneca.model.DBAccessHelper;
import ca.myseneca.model.Employee;

public class HRManagement {

	public HRManagement() {
		// TODO Auto-generated constructor stub
	}
	
	public static void showEmployee(Employee emp) {
		System.out.println("---------------------------------------------------------");	
		System.out.println("--------------showEmployee-------------------------------");	
		System.out.println("---------------------------------------------------------");			
		System.out.println("employee_id : " + emp.getEmployee_id());
		System.out.println("first_name : " + emp.getFirst_name());
		System.out.println("last_name : " + emp.getLast_name());
		System.out.println("email : " + emp.getEmail());
		System.out.println("phone_number : " + emp.getPhone_number());
		System.out.println("hire_date : " + emp.getHire_date());
		System.out.println("job_id : " + emp.getJob_id());
		System.out.println("salary : " + emp.getSalary());
		System.out.println("commission_pct : " + emp.getCommission_pct());
		System.out.println("manager_id : " + emp.getManager_id());
		System.out.println("department_id : " + emp.getDepartment_id());
		System.out.println("---------------------------------------------------------");		
	}
	
	public static void showAllEmployee(ArrayList<Employee> empArrList, String str) {
		
		int count = empArrList.size();
		
		if(count < 1) {
			System.out.println("There is no row in Employee table!");
			return;
		}
		
		System.out.println("---------------------------------------------------------------------");	
		System.out.println("--------------showEmployee By " + str + "----------------");	
		System.out.println("---------------------------------------------------------------------");	
		
		for(int i=0; i<count; i++){
			System.out.println("---------------------------------------------------------");
			System.out.println("employee_id : " + empArrList.get(i).getEmployee_id());
			System.out.println("first_name : " + empArrList.get(i).getFirst_name());
			System.out.println("last_name : " + empArrList.get(i).getLast_name());
			System.out.println("email : " + empArrList.get(i).getEmail());
			System.out.println("phone_number : " + empArrList.get(i).getPhone_number());
			System.out.println("hire_date : " + empArrList.get(i).getHire_date());
			System.out.println("job_id : " + empArrList.get(i).getJob_id());
			System.out.println("salary : " + empArrList.get(i).getSalary());
			System.out.println("commission_pct : " + empArrList.get(i).getCommission_pct());
			System.out.println("manager_id : " + empArrList.get(i).getManager_id());
			System.out.println("department_id : " + empArrList.get(i).getDepartment_id());
			System.out.println("---------------------------------------------------------");
		}
	}
	
	public static void main(String[] args) {
		
		int count = 0;
		Employee emp = null;
		
		//get user id and password
		Scanner inputReader = new Scanner(System.in);
		System.out.println("Please Input UserId to authenticate.");
		String user = inputReader.next();
		System.out.println("Please Input Password to authenticate.");
		String password = inputReader.next();
		inputReader.close();
				
		System.out.println("user :" + user);
		System.out.println("password :" + password);
		
		//log in for authorized user
		int employee_id = DBAccessHelper.getEmployeeID(user, password);
		
		System.out.println("employee_id :" + employee_id);
		
		//if authentication doesn't pass, escape the application
		if (employee_id <= 0) 
			return;
		
		//show authorized user's information 
		emp = DBAccessHelper.getEmployeeByID(employee_id);
		showEmployee(emp);
			
		//show all employees
		ArrayList<Employee> empArrList = DBAccessHelper.getAllEmployees();
		showAllEmployee(empArrList, "method - getAllEmployees");	
		
		//show all employees by departmenId
		int depid = 50;
		empArrList = DBAccessHelper.getEmployeesByDepartmentID(depid);
		showAllEmployee(empArrList,  "method - getEmployeesByDepartmentID");	
		
		//delete one Employee
		emp = new Employee();
		emp.setEmployee_id(800);
		
		count = DBAccessHelper.deleteEmployee(emp.getEmployee_id());
		
		java.util.Date javaDate = new java.util.Date(); 
		long javaTime = javaDate.getTime();
		Date sqlDate = new Date(javaTime);
		
		//add new Employee
		emp = new Employee();
		emp.setEmployee_id(800);
		emp.setFirst_name("JinGeul");
		emp.setLast_name("Kim");
		emp.setEmail("jgkim5@myseneca.ca");
		emp.setJob_id("SH_CLERK");
		emp.setCommission_pct(0.0);
		emp.setSalary(5000.0);
		emp.setHire_date(sqlDate);
		emp.setPhone_number("647.999.9999");
		emp.setManager_id(124);
		emp.setDepartment_id(50);
		
		DBAccessHelper.addNewEmployee(emp);
		
		//modify exist Employee
		emp.setEmployee_id(800);
		emp.setFirst_name("Jin");
		emp.setLast_name("Kim1");
		emp.setEmail("jgkim1@myseneca.ca");
		emp.setJob_id("MK_MAN");
		emp.setCommission_pct(0.8);
		emp.setSalary(9000.0);
		emp.setHire_date(sqlDate);
		emp.setPhone_number("647.888.9999");
		emp.setManager_id(100);
		emp.setDepartment_id(20);		
		
		count = DBAccessHelper.updateEmployee(emp);
				
		//delete one Employee
		emp = new Employee();

		count = DBAccessHelper.deleteEmployee(900);
		count = DBAccessHelper.deleteEmployee(901);
				
		//perform batch job
		String[] sql = { 
		   "INSERT INTO employees VALUES(900, 'k', 'k', 'kkk@email902.com', '888.999.7777', sysdate, 'PR_REP', 1000.0, 0.0, 201, 10)" 
		  ,"INSERT INTO employees VALUES(901, 'j', 'j', 'jjj@email903.com', '111.999.7777', sysdate, 'MK_MAN', 2000.0, 0.0, 100, 20)" 
		  ,"UPDATE employees SET first_name = 'Jin', last_name = 'Km' WHERE employee_id = 900"	
		  ,"UPDATE employees SET first_name = 'Mn', last_name = 'Kim' WHERE employee_id = 901"		
		};
		
		boolean bRet = DBAccessHelper.batchUpdate(sql);
		
		if(bRet)		
			System.out.println("Successful Batch Update");
		
	}

}
