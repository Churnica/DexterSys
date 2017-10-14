package com.dextersys.inheritance;

public class Contractor extends Person {

	public Contractor() {
		super.salary = "$110/hr";
		
		System.out.println("Salary: " + getSalary());
	}

	
}

	
