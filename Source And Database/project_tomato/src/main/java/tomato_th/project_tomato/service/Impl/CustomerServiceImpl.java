package tomato_th.project_tomato.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tomato_th.project_tomato.model.Customer;
import tomato_th.project_tomato.repository.CustomerRepository;
import tomato_th.project_tomato.service.CustomerService;


import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomer() {
        try{
            List<Customer> list = customerRepository.findAll();
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerById(int id) {
        try{
            Customer customer = customerRepository.findById(id).get();
            return customer;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        try{
            customerRepository.save(customer);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer lgoinCustomer(String email, String pass) {
        try {
            Customer customer = customerRepository.getCustomerByEmailPass(email,pass);
            return customer;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        try{
            Customer customer = customerRepository.findByEmail(email);
            return customer;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Customer> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.customerRepository.findAll(pageable);
    }
}
