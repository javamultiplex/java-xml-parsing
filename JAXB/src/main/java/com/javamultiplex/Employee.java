package com.javamultiplex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"name","age","role","salary"})
public class Employee {

	private int id;
	private String name;
	private int age;
	private int salary;
	private String role;
	public int getId() {
		return id;
	}
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	@XmlElement
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getRole() {
		return role;
	}
	@XmlElement
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Employee[id="+getId()+", name="+getName()+", age="+getAge()+", salary="+getSalary()+", role="+getRole()+"]";
	}
}
