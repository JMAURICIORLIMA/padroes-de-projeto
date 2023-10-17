package jmauricio.com.github.padroesprojetospring.repository;

import jmauricio.com.github.padroesprojetospring.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
