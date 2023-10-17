package jmauricio.com.github.padroesprojetospring.service;

import jmauricio.com.github.padroesprojetospring.model.Customer;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente.
 * Com isso, se necessário, podemos ter multiplas implementaçãoes dessa mesma interface
 */
public interface CustomerService {

    Iterable<Customer> getAll();

    Customer getById(Long id);

    void addCustomer(Customer customer);

    void updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

}
