package jmauricio.com.github.padroesprojetospring.service;

import jmauricio.com.github.padroesprojetospring.model.Customer;
import org.springframework.stereotype.Service;

/**
 * Implementação da <b>Strategy</b> {@link CustomerService}, a qual pode ser injetada pelo
 * Spring (via {@link Autowired}). Com isso, como essa classe é um {@link Service},
 * ele será tratada como um <b>Singleton</b>.
 */

@Service
public class CustomerServiceImplements implements CustomerService {

//    TODO Singleton: Injetar os componentes do Spring com @Autowired.
//    TODO Stragegy: Implementar os métodos definidos na interface.
//    TODO Facade: Abstrair integrações com subsistemas, provendo uma interface simples.
    @Override
    public Iterable<Customer> getAll() {
//        FIXME Buscar todos os Clientes.
        return null;
    }

    @Override
    public Customer getById(Long id) {
//        FIXME Buscar Cliente por ID.
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {
//        FIXME Vefificar se o Endereço do Cliente já existe (pelo CEP).
//        FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
//        FIXME Inserir Cliente, vinculando o Endereço (novo ou exixtente).
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
//        FIXME Buscar Cliente por ID, caso exita:
//        FIXME Vefificar se o Endereço do Cliente já existe (pelo CEP).
//        FIXME Caso não exista, integrar com o ViaCEP e persistir o retorno.
//        FIXME Alterar Cliente, vinculando o Endereço (novo ao exixtente).
    }

    @Override
    public void deleteCustomer(Long id) {
//        FIXME Deletar Cliente por ID.
    }
}
