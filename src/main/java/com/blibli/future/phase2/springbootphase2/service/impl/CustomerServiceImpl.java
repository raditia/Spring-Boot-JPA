package com.blibli.future.phase2.springbootphase2.service.impl;

import com.blibli.future.phase2.springbootphase2.model.Address;
import com.blibli.future.phase2.springbootphase2.model.Customer;
import com.blibli.future.phase2.springbootphase2.repository.CustomerRepository;
import com.blibli.future.phase2.springbootphase2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final int PAGE_SIZE = 3;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer register(String name, Address address) {
        Customer customer = new Customer();

        address.setId(UUID.randomUUID().toString());

        customer.setId(UUID.randomUUID().toString());
        customer.setName(name);
        customer.addAddress(address);

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(String id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Page<Customer> getAllCustomers(int pageNumber) {
        Pageable pageable = new PageRequest(pageNumber-1, PAGE_SIZE);
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer deleteCustomer(String id) {
        Customer customer = customerRepository.findOne(id);

        if (customer != null) {
            customerRepository.delete(id);
        }

        return customer;
    }

    @Override
    public Customer editCustomer(Customer customer) {
        return customerRepository.exists(customer.getId()) ? customerRepository.save(customer) : null;
    }
}
