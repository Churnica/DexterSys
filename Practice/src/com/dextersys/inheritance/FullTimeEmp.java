package com.dextersys.inheritance;

public class FullTimeEmp extends Person{

	public FullTimeEmp() {
		super.Department = "Adminstration";
		
		System.out.println("Department: " +  getDepartment());
	}	
	}

