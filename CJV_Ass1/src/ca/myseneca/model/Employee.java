package ca.myseneca.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Employee implements Serializable {
	
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;	
	private String phone_number;
	private Date hire_date;	
	private String job_id;
	private Double salary;	
	private Double commission_pct;
	private int manager_id;	
	private int department_id;	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getCommission_pct() {
		return commission_pct;
	}

	public void setCommission_pct(Double commission_pct) {
		this.commission_pct = commission_pct;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commission_pct == null) ? 0 : commission_pct.hashCode());
		result = prime * result + department_id;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employee_id;
		result = prime * result
				+ ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result
				+ ((hire_date == null) ? 0 : hire_date.hashCode());
		result = prime * result + ((job_id == null) ? 0 : job_id.hashCode());
		result = prime * result
				+ ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + manager_id;
		result = prime * result
				+ ((phone_number == null) ? 0 : phone_number.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (commission_pct == null) {
			if (other.commission_pct != null)
				return false;
		} else if (!commission_pct.equals(other.commission_pct))
			return false;
		if (department_id != other.department_id)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (hire_date == null) {
			if (other.hire_date != null)
				return false;
		} else if (!hire_date.equals(other.hire_date))
			return false;
		if (job_id == null) {
			if (other.job_id != null)
				return false;
		} else if (!job_id.equals(other.job_id))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (manager_id != other.manager_id)
			return false;
		if (phone_number == null) {
			if (other.phone_number != null)
				return false;
		} else if (!phone_number.equals(other.phone_number))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name="
				+ first_name + ", last_name=" + last_name + ", email=" + email
				+ ", phone_number=" + phone_number + ", hire_date=" + hire_date
				+ ", job_id=" + job_id + ", salary=" + salary
				+ ", commission_pct=" + commission_pct + ", manager_id="
				+ manager_id + ", department_id=" + department_id + "]";
	}

}
