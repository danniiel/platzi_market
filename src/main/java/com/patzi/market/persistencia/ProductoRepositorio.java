package com.patzi.market.persistencia;

import com.patzi.market.domain.Product;
import com.patzi.market.domain.repositorio.ProductRepository;
import com.patzi.market.persistencia.crud.ProductoCrudRepositorio;
import com.patzi.market.persistencia.entidades.Producto;
import com.patzi.market.persistencia.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepositorio implements ProductRepository{
    @Autowired
    private ProductoCrudRepositorio productoCrudRepositorio;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepositorio.findAll();
        return mapper.toProducts(productos);

    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepositorio.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }


    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepositorio.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepositorio.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepositorio.save(producto));
    }


    @Override
    public void delete(int productId){
        productoCrudRepositorio.deleteById(productId);
    }
}
