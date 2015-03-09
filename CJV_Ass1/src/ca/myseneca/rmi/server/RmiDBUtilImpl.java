package ca.myseneca.rmi.server;

import java.util.ArrayList;
import ca.myseneca.model.DBAccessHelper;
import ca.myseneca.model.Employee;

public class RmiDBUtilImpl extends java.rmi.server.UnicastRemoteObject implements RmiDBUtilInterface  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RmiDBUtilImpl() throws java.rmi.RemoteException  {
		// TODO Auto-generated constructor stub
		super();
	}

	public int getEmployeeID(String user, String password) throws java.rmi.RemoteException  {
		return DBAccessHelper.getEmployeeID(user, password);
	}
	
	public Employee getEmployeeByID(int empid) throws java.rmi.RemoteException  {
		return DBAccessHelper.getEmployeeByID(empid);
	}	
	
	public ArrayList<Employee> getAllEmployees() throws java.rmi.RemoteException  {
		return DBAccessHelper.getAllEmployees();
	}

	public ArrayList<Employee> getEmployeesByDepartmentID(int depid) throws java.rmi.RemoteException  {
		return DBAccessHelper.getEmployeesByDepartmentID(depid);
	}	
	
	public void addNewEmployee(Employee emp) throws java.rmi.RemoteException  {
		DBAccessHelper.addNewEmployee(emp);
	}	
	
	public int updateEmployee(Employee emp) throws java.rmi.RemoteException  {
		return DBAccessHelper.updateEmployee(emp);
	}	
	
	public int deleteEmployee(int empid) throws java.rmi.RemoteException  {
		return deleteEmployee(empid);
	}		
	
	public boolean batchUpdate(String[] SQLs) throws java.rmi.RemoteException  {
		return batchUpdate(SQLs);
  }	
	
}
