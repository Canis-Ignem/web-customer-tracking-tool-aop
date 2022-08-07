package com.jon.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jon.springdemo.entity.Customer;



@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		//get a query
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		//execute query
		List<Customer> customers = query.getResultList();
		//return customer list
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		//get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		//get customer
		Customer customer = session.get(Customer.class, id);

		//return customer
		return customer;
		
	}

	@Override
	public void deleteCustomer(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
		
	}

}
