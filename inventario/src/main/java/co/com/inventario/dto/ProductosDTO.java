package co.com.inventario.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.inventario.utility.Utilities;


/**
 * @author Edwin Gonzalez
 *
 */
public class ProductosDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date fechacreacion;
	private Date fechamodificacion;
	private Integer idproducto;
	private String nombreproducto;

	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Integer getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombreproducto() {
		return nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}

	@Override
	public String toString() {
		return Utilities.toStringObjec(this);
	}
}
