package tomato_th.project_tomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tomato_th.project_tomato.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1 AND c.password = ?2 ")
    Customer getCustomerByEmailPass(String email, String pass);


}
