package com.homeworkhelper;

/*
 * This object holds the information for a class. 
 */
public class Classes {

	private int credits;
	private String className;
	private double gpa;
	
	public Classes(String name, int credits)
	{
		className = name;
		this.credits = credits;
		gpa = 0.0;
	}
	
	public void setCredits(int credits)
	{
		this.credits = credits;
	}
	
	public int getCredits()
	{
		return credits;
	}
	
	public void setClassName(String name)
	{
		className = name;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public void setGpa(double gpa)
	{
		this.gpa = gpa;
	}
	
	public double getGpa()
	{
		return gpa;
	}
	
}
