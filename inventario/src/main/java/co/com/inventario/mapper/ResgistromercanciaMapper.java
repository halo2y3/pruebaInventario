package co.com.inventario.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.inventario.dto.ResgistromercanciaDTO;
import co.com.inventario.modelo.Resgistromercancia;


/**
* @author Edwin Gonzalez
*/
@Mapper(imports = co.com.inventario.utility.Utilities.class)
public interface ResgistromercanciaMapper {
	
    @Mapping(source = "productos.idproducto", target = "idproductoProductos")
    @Mapping(source = "usuariosByUsuarioidmodificador.idusuario", target = "idusuarioModificador")
    @Mapping(source = "usuariosByUsuarioidregistrador.idusuario", target = "idusuarioRegistrador")
    @Mapping(target = "nombreproducto", ignore = true)
    public ResgistromercanciaDTO resgistromercanciaToResgistromercanciaDTO(Resgistromercancia resgistromercancia);

    @Mapping(source = "idproductoProductos", target = "productos.idproducto")
    @Mapping(target = "usuariosByUsuarioidmodificador", expression = "java((Utilities.crearUsuario(resgistromercanciaDTO.getIdusuarioModificador())))")
    @Mapping(target = "usuariosByUsuarioidregistrador", expression = "java((Utilities.crearUsuario(resgistromercanciaDTO.getIdusuarioRegistrador())))")
    public Resgistromercancia resgistromercanciaDTOToResgistromercancia(ResgistromercanciaDTO resgistromercanciaDTO);

    public List<ResgistromercanciaDTO> listResgistromercanciaToListResgistromercanciaDTO(List<Resgistromercancia> resgistromercancias);

    public List<Resgistromercancia> listResgistromercanciaDTOToListResgistromercancia(List<ResgistromercanciaDTO> resgistromercanciaDTOs);
}
