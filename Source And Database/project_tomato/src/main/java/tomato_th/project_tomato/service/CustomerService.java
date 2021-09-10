package tomato_th.project_tomato.service;

import org.springframework.data.domain.Page;
import tomato_th.project_tomato.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();
    Customer getCustomerById(int id);
    boolean saveCustomer(Customer customer);

    Customer lgoinCustomer(String email, String pass);
    Customer findByEmail(String email);
    Page<Customer> findPaginated(int pageNo, int pageSize);
}
