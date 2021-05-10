package co.com.inventario.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.inventario.dto.UsuariosDTO;
import co.com.inventario.modelo.Usuarios;


/**
* @author Edwin Gonzalez
*/
@Mapper
public interface UsuariosMapper {
	
    @Mapping(source = "cargos.idcargo", target = "idcargoCargos")
    public UsuariosDTO usuariosToUsuariosDTO(Usuarios usuarios);

    @Mapping(source = "idcargoCargos", target = "cargos.idcargo")
    @Mapping(target = "resgistromercanciasForUsuarioidmodificador", ignore = true)
    @Mapping(target = "resgistromercanciasForUsuarioidregistrador", ignore = true)
    public Usuarios usuariosDTOToUsuarios(UsuariosDTO usuariosDTO);

    public List<UsuariosDTO> listUsuariosToListUsuariosDTO(
        List<Usuarios> usuarioss);

    public List<Usuarios> listUsuariosDTOToListUsuarios(
        List<UsuariosDTO> usuariosDTOs);
}
