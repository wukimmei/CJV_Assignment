package ca.myseneca.model;

import oracle.jdbc.*;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class DBAccessHelper  {

	public DBAccessHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public static int getEmployeeID(String user, String password) {
		
		Connection conn = DBUtilities.getConnection();
		int employee_id = 0;
		CallableStatement cstmt = null;
		
		try {
			cstmt = conn.prepareCall("{? =call cjv805_151a05.P_SECURITY.F_SECURITY(?,?)}");
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, user);
			cstmt.setString(3, password);
			
			cstmt.execute();
					
			employee_id = cstmt.getInt(1);
		} catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
		} finally {
			DBUtilities.closeCallableStatement(cstmt);
			DBUtilities.closeConnection(conn);	
		}
		
		return employee_id;
	
	}
	
	public static Employee getEmployeeByID(int empid) {
		
		Connection conn = DBUtilities.getConnection();
		Employee emp = new Employee();
		OracleCallableStatement ostmt = null;
		OracleResultSet orset = null;
		
		try {
				
				ostmt = (OracleCallableStatement)conn.prepareCall("{call cjv805_151a05.P_SECURITY.P_EMP_INFO(?,?)}");
				
				ostmt.setInt(1, empid );
				ostmt.registerOutParameter(2, OracleTypes.CURSOR);
				ostmt.execute();
				
				orset = (OracleResultSet) ostmt.getCursor(2);
				
				while (orset.next()) {
					emp.setEmployee_id(orset.getInt(1));
					emp.setFirst_name(orset.getString(2));
					emp.setLast_name(orset.getString(3));
					emp.setEmail(orset.getString(4));
					emp.setPhone_number(orset.getString(5));
					emp.setHire_date(orset.getDate(6));
					emp.setJob_id(orset.getString(7));
					emp.setSalary(orset.getDouble(8));
					emp.setCommission_pct(orset.getDouble(9));
					emp.setManager_id(orset.getInt(10));
					emp.setDepartment_id(orset.getInt(11));
				}
				
		} catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
		} finally {
			DBUtilities.closeResultSet(orset);
			DBUtilities.closeOracleCallableStatement(ostmt);
			DBUtilities.closeConnection(conn);	
		}
		
		return emp;
	
	}	
	
	public static ArrayList<Employee> getAllEmployees() {
		
		Connection conn = DBUtilities.getConnection();
		
		ArrayList<Employee> empList = new ArrayList<Employee>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select employee_id, first_name, last_name, email, phone_number, hire_date, job_id, "
				+ " salary, commission_pct, manager_id, department_id from employees order by employee_id" ;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployee_id(rs.getInt("employee_id"));
				emp.setFirst_name(rs.getString("first_name"));
				emp.setLast_name(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhone_number(rs.getString("phone_number"));
				emp.setHire_date(rs.getDate("hire_date"));
				emp.setJob_id(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommission_pct(rs.getDouble("commission_pct"));
				emp.setManager_id(rs.getInt("manager_id"));
				emp.setDepartment_id(rs.getInt("department_id"));
				
				empList.add(emp);
			}
				
		} catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
		} finally {
			DBUtilities.closePreparedStatement(pstmt);
			DBUtilities.closeConnection(conn);
		}
		
		return empList;
	
	}

	public static ArrayList<Employee> getEmployeesByDepartmentID(int depid) {
		
		Connection conn = DBUtilities.getConnection();
		
		ArrayList<Employee> empList = new ArrayList<Employee>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select employee_id, first_name, last_name, email, phone_number, hire_date, job_id, "
				+ "          salary, commission_pct, manager_id, department_id from employees where department_id = ? ; " ;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, depid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployee_id(rs.getInt("employee_id"));
				emp.setFirst_name(rs.getString("first_name"));
				emp.setLast_name(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhone_number(rs.getString("phone_number"));
				emp.setHire_date(rs.getDate("hire_date"));
				emp.setJob_id(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommission_pct(rs.getDouble("commission_pct"));
				emp.setManager_id(rs.getInt("manager_id"));
				emp.setDepartment_id(rs.getInt("department_id"));
				
				empList.add(emp);
			}
				
		} catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
		} finally {
			DBUtilities.closePreparedStatement(pstmt);
			DBUtilities.closeConnection(conn);
		}
		
		return empList;
	
	}	
	
	
	public static void addNewEmployee(Employee emp) {
		
		Connection conn = DBUtilities.getConnection();
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO employees " +
		"(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) " + 
		"VALUES " +
		"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt   (1,  emp.getEmployee_id()   );
			pstmt.setString(2,  emp.getFirst_name()    );
			pstmt.setString(3,  emp.getLast_name()     );
			pstmt.setString(4,  emp.getEmail()         );
			pstmt.setString(5,  emp.getPhone_number()  );
			pstmt.setDate  (6,  emp.getHire_date()     );
			pstmt.setString(7,  emp.getJob_id()        );
			pstmt.setDouble(8,  emp.getSalary()        );
			pstmt.setDouble(9,  emp.getCommission_pct());
			pstmt.setInt   (10, emp.getManager_id()    );
			pstmt.setInt   (11, emp.getDepartment_id() );
			
			int count = pstmt.executeUpdate();
			
			conn.commit();
			
			System.out.println(count + " Row Created with Employee ID :" + emp.getEmployee_id());

		} catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
		} finally {
			DBUtilities.closePreparedStatement(pstmt);
			DBUtilities.closeConnection(conn);
		}
	
	}	
	
	public static int updateEmployee(Employee emp) {
		
		Connection conn = DBUtilities.getConnection();
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE employees SET " +
		             "first_name  = ?, last_name  = ?, email  = ?, phone_number  = ?, hire_date  = ?, " +
		             "job_id  = ?, salary  = ?, commission_pct  = ?, manager_id  = ?, department_id  = ? " +
		             "WHERE employee_id = ? "
		;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  emp.getFirst_name()    );
			pstmt.setString(2,  emp.getLast_name()     );
			pstmt.setString(3,  emp.getEmail()         );
			pstmt.setString(4,  emp.getPhone_number()  );
			pstmt.setDate  (5,  emp.getHire_date()     );
			pstmt.setString(6,  emp.getJob_id()        );
			pstmt.setDouble(7,  emp.getSalary()        );
			pstmt.setDouble(8,  emp.getCommission_pct());
			pstmt.setInt   (9,  emp.getManager_id()    );
			pstmt.setInt   (10, emp.getDepartment_id() );
			pstmt.setInt   (11, emp.getEmployee_id()   );
			
			int count = pstmt.executeUpdate();
			
			conn.commit();
			
			System.out.println(count + " Row Updated with Employee ID :" + emp.getEmployee_id());
			
			return count;

		} catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
			return 0;
		} finally {
			DBUtilities.closePreparedStatement(pstmt);
			DBUtilities.closeConnection(conn);
		}
	
	}	
	
	public static int deleteEmployee(int empid) {
		
		Connection conn = DBUtilities.getConnection();
		
		PreparedStatement pstmt = null;
		
		System.out.println("deleteEmployee--1--");
		
		String sql = "DELETE FROM employees WHERE employee_id = ? ";
		
		System.out.println("deleteEmployee--2--");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("deleteEmployee--3--");
			
			pstmt.setInt   (1, empid);

			System.out.println("deleteEmployee--4--");
			
			int count = pstmt.executeUpdate();

			System.out.println("deleteEmployee--5--");
			
			conn.commit();
						
			System.out.println(count + " Row Deleted with Employee ID :" + empid);
			
			return count;

		} catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
			return 0;
		} finally {
			DBUtilities.closePreparedStatement(pstmt);
			DBUtilities.closeConnection(conn);
		}
	
	}		
	
	public static boolean batchUpdate(String[] SQLs) {
		
		Connection conn = DBUtilities.getConnection();

		Statement stmt = null;
		
	    try {

	      conn.setAutoCommit(false);
	      	      
	      stmt = conn.createStatement();
	      
	      stmt.clearBatch();
	      
	      for(int i = 0; i < SQLs.length; i++) {
	    	  stmt.addBatch(SQLs[i]);
	      }

	      int[] updateCounts = stmt.executeBatch();
	      conn.commit();
	      
	      return true;

	    } catch (BatchUpdateException b) {
	    	DBUtilities.printBatchUpdateException(b);
			b.printStackTrace();
	    	return false;
	    } catch (SQLException e) {
			DBUtilities.printSQLException(e);
			e.printStackTrace();
	    	return false;
	    } finally {
			DBUtilities.closeStatement(stmt);
			DBUtilities.closeConnection(conn);

	    }
  }	
	
}
