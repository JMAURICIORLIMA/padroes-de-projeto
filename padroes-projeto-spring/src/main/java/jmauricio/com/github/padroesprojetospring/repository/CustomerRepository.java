package jmauricio.com.github.padroesprojetospring.repository;

import jmauricio.com.github.padroesprojetospring.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
