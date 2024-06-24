package com.patzi.market.persistencia.mapper;

import com.patzi.market.domain.PurchaseItem;
import com.patzi.market.persistencia.entidades.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
      @Mapping(source = "id.idProducto", target = "productId"),
      @Mapping(source = "cantidad", target = "quantity"),
      @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto productos);

    @InheritInverseConfiguration
    @Mappings({
      @Mapping(target = "compra", ignore = true),
      @Mapping(target = "producto", ignore = true),
      @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
