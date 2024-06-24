package com.patzi.market.persistencia.crud;

import com.patzi.market.persistencia.entidades.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, String> {

    Optional<List<Compra>> findByIdCliente(String idCliente);
}
