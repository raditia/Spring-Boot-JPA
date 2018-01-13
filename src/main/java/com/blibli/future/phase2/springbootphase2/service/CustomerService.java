package com.blibli.future.phase2.springbootphase2.service;

import com.blibli.future.phase2.springbootphase2.model.Address;
import com.blibli.future.phase2.springbootphase2.model.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Customer register(String name, Address address);
    Customer getCustomer(String id);
    Page<Customer> getAllCustomers(int pageNumber);
    Customer deleteCustomer(String id);
    Customer editCustomer(Customer customer);
}
