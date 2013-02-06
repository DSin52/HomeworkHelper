package com.homeworkhelper;

import java.util.ArrayList;

/*
 * Keeps track of the classes. 
 */
public class ClassModel {

	private ArrayList<Classes> classes;

	
	public ClassModel()
	{
		classes = new ArrayList<Classes>();
	}
	
	public void addClass(Classes className)
	{
		classes.add(className);
	}
	
	public Classes getClass(int index)
	{
		return classes.get(index);
	}
	
	
	
}
