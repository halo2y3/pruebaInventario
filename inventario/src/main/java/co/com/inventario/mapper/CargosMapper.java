package co.com.inventario.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.inventario.dto.CargosDTO;
import co.com.inventario.modelo.Cargos;


/**
* @author Edwin Gonzalez
*/
@Mapper
public interface CargosMapper {
    public CargosDTO cargosToCargosDTO(Cargos cargos);

    @Mapping(target = "usuarioses", ignore = true)
    public Cargos cargosDTOToCargos(CargosDTO cargosDTO);

    public List<CargosDTO> listCargosToListCargosDTO(List<Cargos> cargoss);

    public List<Cargos> listCargosDTOToListCargos(List<CargosDTO> cargosDTOs);
}
