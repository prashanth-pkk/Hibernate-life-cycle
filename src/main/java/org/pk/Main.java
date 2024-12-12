package org.pk;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.pk.entity.Employee;

public class Main {
    public static void main(String[] args) {
        //Create hibernate session factory
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(org.pk.entity.Employee.class)
                .buildSessionFactory();
        //Create session
        Session session = sessionFactory.getCurrentSession();
        try {
            //employee in transient state
            Employee employee = new Employee(1, "jack", "will", "IT", 500000.9);
            Employee employee1 = new Employee(2, "Hema", "malini", "Fashion", 35000.50);
            //Begin transaction
            session.beginTransaction();

            //save the employee(make it persistent)
            session.save(employee);
            session.save(employee1);

            // Commit the transaction (persisting to the database)
            session.getTransaction().commit();

            // Employee is now in Persistent state, saved in the database
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            Employee fetchedEmployee = session.get(Employee.class, 2);
            System.out.println("Fetched Employee details : " + fetchedEmployee.getFirstName());
            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Employee employeeToDelete = session.get(Employee.class, 1);
            if (employeeToDelete != null) {
                //Delete the employee
                session.delete(employeeToDelete);
            }

            session.getTransaction().commit();
            System.out.println("Employee deleted");
        } finally {
            //close session factory
            sessionFactory.close();
        }

    }
}