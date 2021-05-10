package co.com.inventario.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.inventario.dto.ProductosDTO;
import co.com.inventario.modelo.Productos;


/**
* @author Edwin Gonzalez
*/
@Mapper
public interface ProductosMapper {
    
	public ProductosDTO productosToProductosDTO(Productos productos);

	@Mapping(target = "resgistromercancias", ignore = true)
    public Productos productosDTOToProductos(ProductosDTO productosDTO);

    public List<ProductosDTO> listProductosToListProductosDTO(
        List<Productos> productoss);

    public List<Productos> listProductosDTOToListProductos(
        List<ProductosDTO> productosDTOs);
}
