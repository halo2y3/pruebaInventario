package co.com.inventario.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.inventario.utility.Utilities;


/**
 * @author Edwin Gonzalez
 *
 */
public class UsuariosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer edad;
	private Date fechaingreso;
	private Integer idusuario;
	private String nombreusuario;
	private Integer idcargoCargos;

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public Integer getIdcargoCargos() {
		return idcargoCargos;
	}

	public void setIdcargoCargos(Integer idcargoCargos) {
		this.idcargoCargos = idcargoCargos;
	}

	@Override
	public String toString() {
		return Utilities.toStringObjec(this);
	}
}
