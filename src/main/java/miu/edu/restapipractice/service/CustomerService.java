package miu.edu.restapipractice.service;

import miu.edu.restapipractice.model.Customer;
import miu.edu.restapipractice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer c) {
        return customerRepository.save(c);
    }
}
