package com.attilavarga.customer.relationship.manager.service;

import java.util.List;
import com.attilavarga.customer.relationship.manager.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	public void saveCustomer(Customer customer);
	public Customer getCustomer(int id);
	public void deleteCustomer(int id);
	public List<Customer> searchCustomers(String searchName);
}
