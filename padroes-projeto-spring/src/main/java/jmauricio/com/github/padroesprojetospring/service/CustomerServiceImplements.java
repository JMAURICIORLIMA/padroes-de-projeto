package jmauricio.com.github.padroesprojetospring.service;

import jmauricio.com.github.padroesprojetospring.model.Address;
import jmauricio.com.github.padroesprojetospring.model.Customer;
import jmauricio.com.github.padroesprojetospring.repository.AddressRepository;
import jmauricio.com.github.padroesprojetospring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link CustomerService}, a qual pode ser injetada pelo
 * Spring (via {@link Autowired}). Com isso, como essa classe é um {@link Service},
 * ele será tratada como um <b>Singleton</b>.
 */

@Service
public class CustomerServiceImplements implements CustomerService {

//    Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService viaCepService;

//    Stragegy: Implementar os métodos definidos na interface.
//    Facade: Abstrair integrações com subsistemas, provendo uma interface simples.
    @Override
    public Iterable<Customer> getAll() {
//        FIXME Buscar todos os Clientes.
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
//        FIXME Buscar Cliente por ID.
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.get();
    }

    @Override
    public void addCustomer(Customer customer) {
//        FIXME Vefificar se o Endereço do Cliente já existe (pelo CEP).
        saveCustomerWithZipCode(customer);
    }

    private void saveCustomerWithZipCode(Customer customer) {
        String cep = customer.getAdress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
//        FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Address newAddress = viaCepService.zipCodeLookup(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        customer.setAdress(address);
//        FIXME Inserir Cliente, vinculando o Endereço (novo ou exixtente).
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
//        FIXME Buscar Cliente por ID, caso exita:
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
//        FIXME Vefificar se o Endereço do Cliente já existe (pelo CEP).
//        FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
//        FIXME Alterar Cliente, vinculando o Endereço (novo ao exixtente).
            saveCustomerWithZipCode(customer);
        }
    }

    @Override
    public void deleteCustomer(Long id) {
//        FIXME Deletar Cliente por ID.
        customerRepository.deleteById(id);
    }
}
