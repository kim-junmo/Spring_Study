package com.docmall.demo.domain;

import java.util.Date;

/*
EMPLOYEE_ID		NUMBER(6,0)	No		1	사원ID
EMP_NAME		VARCHAR2(80 BYTE)	No		2	사원이름
EMAIL			VARCHAR2(50 BYTE)	Yes		3	이메일
PHONE_NUMBER	VARCHAR2(30 BYTE)	Yes		4	
HIRE_DATE		DATE	No		5	입사일자
SALARY			NUMBER(8,2)	Yes		6	급여
MANAGER_ID		NUMBER(6,0)	Yes		7	상관ID
COMMISSION_PCT	NUMBER(2,2)	Yes		8	커미션
RETIRE_DATE		DATE	Yes		9	퇴사일자
DEPARTMENT_ID	NUMBER(6,0)	Yes		10	
JOB_ID			VARCHAR2(10 BYTE)	Yes		11	
CREATE_DATE		DATE	Yes	SYSDATE	12	생성일자
UPDATE_DATE		DATE	Yes	"SYSDATE"	13	변경일자

 */

public class EmployeeVO {

	private	Integer	employee_id;
	private String	emp_name;
	private String	email;
	private String	phone_number;
	private Date	hire_date;
	private Integer	salary;
	private Integer	manager_id;
	private Integer	commission_pct;
	private Date	retire_date;
	private Integer	department_id;
	private String	job_id;
	private Date	create_date;
	private Date	update_date;
	
	
	
	
	public Integer getEmployee_id() {
		return employee_id;
	}
	
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	public String getEmp_name() {
		return emp_name;
	}
	
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
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
	
	public Integer getSalary() {
		return salary;
	}
	
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	public Integer getManager_id() {
		return manager_id;
	}
	
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	
	public Integer getCommission_pct() {
		return commission_pct;
	}
	
	public void setCommission_pct(Integer commission_pct) {
		this.commission_pct = commission_pct;
	}
	
	public Date getRetire_date() {
		return retire_date;
	}
	
	public void setRetire_date(Date retire_date) {
		this.retire_date = retire_date;
	}
	
	public Integer getDepartment_id() {
		return department_id;
	}
	
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	
	public String getJob_id() {
		return job_id;
	}
	
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public Date getUpdate_date() {
		return update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	@Override
	public String toString() {
		return "EmployeeVO [employee_id=" + employee_id + ", emp_name=" + emp_name + ", email=" + email
				+ ", phone_number=" + phone_number + ", hire_date=" + hire_date + ", salary=" + salary + ", manager_id="
				+ manager_id + ", commission_pct=" + commission_pct + ", retire_date=" + retire_date
				+ ", department_id=" + department_id + ", job_id=" + job_id + ", create_date=" + create_date
				+ ", update_date=" + update_date + "]";
	}
	
	
	
	
	
}
