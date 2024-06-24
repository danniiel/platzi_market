package com.patzi.market.persistencia;

import com.patzi.market.domain.Purchase;
import com.patzi.market.domain.repositorio.PurchaseRepository;
import com.patzi.market.persistencia.crud.CompraCrudRepository;
import com.patzi.market.persistencia.entidades.Compra;
import com.patzi.market.persistencia.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
          .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(productos -> productos.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
