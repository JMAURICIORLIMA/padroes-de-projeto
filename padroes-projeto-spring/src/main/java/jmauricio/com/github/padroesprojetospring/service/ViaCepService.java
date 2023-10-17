package jmauricio.com.github.padroesprojetospring.service;

import jmauricio.com.github.padroesprojetospring.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente HTTP, criado via <b>OpenFeign</b>, para o consumo da API do <b>ViaCEP</b>
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 */

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping(value = "/{cep}/json/")
    Address zipCodeLookup(@PathVariable("cep") String cep);
}
