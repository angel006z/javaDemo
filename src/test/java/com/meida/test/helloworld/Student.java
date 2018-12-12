package com.meida.test.helloworld;

public class Student {
	public Student() {

	}

	public Student(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode(){
		return (this.id+this.name).length();
	}
	
	@Override
    public boolean equals(Object obj) {
        if(! (obj instanceof Student))
            return false;
        if(obj == this)
            return true;
        return this.name.equals(((Student)obj).name);
    }


	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
