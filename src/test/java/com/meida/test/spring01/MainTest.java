package com.meida.test.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String args[]){
		ApplicationContext context= new ClassPathXmlApplicationContext("test/beans01.xml");
        Student student= (Student) context.getBean("student");
        Teacher teacher= (Teacher) context.getBean("teacher");
        System.out.println("学生的姓名："+student.getName()+"。老师是"+student.getTeacher().getName());
        System.out.println("老师的姓名："+teacher.getName());
    }
}
