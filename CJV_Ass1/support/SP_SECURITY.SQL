-------------------
-- PACAKGE       --
-------------------

CREATE OR REPLACE PACKAGE P_SECURITY 
AS
TYPE cur_empinfo IS REF CURSOR;

-- STORED FUNCTION F_SECURITY DECLARE
FUNCTION F_SECURITY
(
  p_sec_id IN SECURITY.SEC_ID%TYPE
 ,p_sec_password IN SECURITY.SEC_PASSWORD%TYPE
)
RETURN NUMBER;

-- STORED PROCEDURE F_SECURITY DECLARE
PROCEDURE P_EMP_INFO 
(
  p_employee_id IN EMPLOYEES.EMPLOYEE_ID%TYPE
 ,p_info OUT cur_empinfo 
) ;    

END P_SECURITY;
/

-------------------
-- PACAKGE  BODY --
-------------------
CREATE OR REPLACE PACKAGE BODY P_SECURITY 
AS

-- STORED FUNCTION F_SECURITY
FUNCTION F_SECURITY
(
  p_sec_id IN SECURITY.SEC_ID%TYPE
 ,p_sec_password IN SECURITY.SEC_PASSWORD%TYPE
)
RETURN NUMBER
IS
    v_employee_id EMPLOYEES.EMPLOYEE_ID%TYPE;

BEGIN

    v_employee_id := 0;

    SELECT employee_id 
      INTO v_employee_id 
      FROM security
     WHERE sec_id = p_sec_id
       AND sec_password = p_sec_password
       AND sec_status = 'A'
     ;
    
    RETURN v_employee_id;
    
EXCEPTION 
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
    WHEN OTHERS THEN
        RETURN -1;


END F_SECURITY;

-- STORED PROCEDURE P_EMP_INFO
PROCEDURE P_EMP_INFO
(
  p_employee_id IN EMPLOYEES.EMPLOYEE_ID%TYPE
 ,p_info OUT cur_empinfo 
) 
IS

BEGIN

  OPEN p_info FOR 
  SELECT 
	     EMPLOYEE_ID                                               
		,FIRST_NAME                                                
		,LAST_NAME                                                 
		,EMAIL                                                     
		,PHONE_NUMBER                                              
		,HIRE_DATE                                                 
		,JOB_ID                                                    
		,SALARY                                                    
		,COMMISSION_PCT                                            
		,MANAGER_ID                                                
		,DEPARTMENT_ID   
    FROM employees
   WHERE employee_id = p_employee_id 
	;

EXCEPTION WHEN OTHERS THEN
  p_info := NULL;

END P_EMP_INFO;

END P_SECURITY;

-------------------
-- TEST FUNCTION --
-------------------
SET SERVEROUTPUT ON;
SET VERIFY OFF;

DECLARE 
    v_sec_id SECURITY.SEC_ID%TYPE;
    v_sec_password SECURITY.SEC_PASSWORD%TYPE;
    v_ret NUMBER;
    
BEGIN

    v_sec_id := '&in_sec_id';
    v_sec_password := '&in_sec_password';
    
    v_ret := P_SECURITY.F_SECURITY(v_sec_id, v_sec_password);
        
    DBMS_OUTPUT.PUT_LINE('return value : ' || v_ret);
    
    IF v_ret = 1 THEN
      DBMS_OUTPUT.PUT_LINE('LOG IN SUCCESS');
    ELSE
      DBMS_OUTPUT.PUT_LINE('LOG IN FAILURE');
    END IF;

END ;    


--------------------
-- TEST PROCEDURE --
--------------------
SET SERVEROUTPUT ON;
SET VERIFY OFF;

DECLARE 
    cur_empinfo SYS_REFCURSOR ;
	
    p_employee_id EMPLOYEES.EMPLOYEE_ID%TYPE;	
  
    v_employee_id    EMPLOYEES.EMPLOYEE_ID%TYPE;
    v_first_name     EMPLOYEES.FIRST_NAME%TYPE;
    v_last_name      EMPLOYEES.LAST_NAME%TYPE;
    v_email          EMPLOYEES.EMAIL%TYPE;
    v_phone_number   EMPLOYEES.PHONE_NUMBER%TYPE;
    v_hire_date      EMPLOYEES.HIRE_DATE%TYPE;
    v_job_id         EMPLOYEES.JOB_ID%TYPE;
    v_salary         EMPLOYEES.SALARY%TYPE;
    v_commission_pct EMPLOYEES.COMMISSION_PCT%TYPE;
    v_manager_id     EMPLOYEES.MANAGER_ID%TYPE;
    v_department_id  EMPLOYEES.DEPARTMENT_ID%TYPE;
   
BEGIN

    p_employee_id := &in_employee_id;
    
    P_SECURITY.P_EMP_INFO(p_employee_id, cur_empinfo);
    
    LOOP 
        FETCH cur_empinfo
        INTO  v_employee_id   
			 ,v_first_name    
			 ,v_last_name     
			 ,v_email         
			 ,v_phone_number  
			 ,v_hire_date     
			 ,v_job_id        
			 ,v_salary        
			 ,v_commission_pct
			 ,v_manager_id    
			 ,v_department_id 
	    ;	
		
        EXIT WHEN cur_empinfo%NOTFOUND;
		
        DBMS_OUTPUT.PUT_LINE('employee_id :' || v_employee_id);
        DBMS_OUTPUT.PUT_LINE('first_name :' || v_first_name);
        DBMS_OUTPUT.PUT_LINE('last_name :' || v_last_name);
        DBMS_OUTPUT.PUT_LINE('email :' || v_email);
        DBMS_OUTPUT.PUT_LINE('phone_number :' || v_phone_number);
        DBMS_OUTPUT.PUT_LINE('hire_date :' || v_hire_date);
        DBMS_OUTPUT.PUT_LINE('job_id :' || v_job_id);
        DBMS_OUTPUT.PUT_LINE('salary :' || v_salary);
        DBMS_OUTPUT.PUT_LINE('commission_pct :' || v_commission_pct);
        DBMS_OUTPUT.PUT_LINE('department_id :' || v_department_id);
	
    END LOOP;
          
    CLOSE cur_empinfo;    
   
END ;











