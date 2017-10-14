package com.dextersys.inheritance;

public class Test {

	public static void main(String[] args) {
			
			Person P = new Intern();
			P.getAge();
			Person P1 = new FullTimeEmp();
			P1.getDepartment();
			Person P2 = new Contractor();
			P2.getSalary();
			
			
	}

}
