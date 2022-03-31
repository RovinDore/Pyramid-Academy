package org.genspark;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args ){
//        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
//        Student obj = (Student) context.getBean("Student");
//        System.out.println(obj);

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Employee obj = (Employee) context.getBean(Employee.class);
        System.out.println(obj);

    }
}
