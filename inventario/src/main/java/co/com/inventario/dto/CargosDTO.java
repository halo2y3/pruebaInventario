package co.com.inventario.dto;

import java.io.Serializable;

import co.com.inventario.utility.Utilities;


/**
* @author Edwin Gonzalez
*
*/
public class CargosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idcargo;
    private String nombrecargo;

    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    @Override
    public String toString() {
    	return Utilities.toStringObjec(this);
    }
}
