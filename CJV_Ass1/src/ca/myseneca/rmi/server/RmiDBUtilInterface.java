package ca.myseneca.rmi.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import ca.myseneca.model.Employee;

public interface RmiDBUtilInterface extends java.rmi.Remote {
    public int getEmployeeID(String user, String password) throws java.rmi.RemoteException;
    public Employee getEmployeeByID(int empid) throws RemoteException;
    public ArrayList<Employee> getAllEmployees() throws RemoteException;
    public ArrayList<Employee> getEmployeesByDepartmentID(int depid) throws RemoteException;
    public void addNewEmployee(Employee emp) throws RemoteException;
    public int updateEmployee(Employee emp) throws RemoteException;
    public int deleteEmployee(int empid) throws RemoteException; 
    public boolean batchUpdate(String[] SQLs) throws RemoteException;
}
