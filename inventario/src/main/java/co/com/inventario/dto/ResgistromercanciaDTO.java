package co.com.inventario.dto;

import java.io.Serializable;
import java.util.Date;

import co.com.inventario.utility.Utilities;


/**
 * @author Edwin Gonzalez
 *
 */
public class ResgistromercanciaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cantidad;
	private Date fechaingresoegreso;
	private Date fechamodificacion;
	private Date fecharegistro;
	private Integer idregistro;
	private String tiporegistro;
	private Integer idproductoProductos;
	private Integer idusuarioRegistrador;
	private Integer idusuarioModificador;
	private String nombreproducto;

	public ResgistromercanciaDTO() {
		super();
	}

	public ResgistromercanciaDTO(Integer cantidad, Date fechaingresoegreso, Date fechamodificacion, Date fecharegistro,
			Integer idregistro, String tiporegistro, Integer idproductoProductos, Integer idusuarioRegistrador,
			Integer idusuarioModificador, String nombreproducto) {
		super();
		this.cantidad = cantidad;
		this.fechaingresoegreso = fechaingresoegreso;
		this.fechamodificacion = fechamodificacion;
		this.fecharegistro = fecharegistro;
		this.idregistro = idregistro;
		this.tiporegistro = tiporegistro;
		this.idproductoProductos = idproductoProductos;
		this.idusuarioRegistrador = idusuarioRegistrador;
		this.idusuarioModificador = idusuarioModificador;
		this.nombreproducto = nombreproducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaingresoegreso() {
		return fechaingresoegreso;
	}

	public void setFechaingresoegreso(Date fechaingresoegreso) {
		this.fechaingresoegreso = fechaingresoegreso;
	}

	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public Date getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Integer getIdregistro() {
		return idregistro;
	}

	public void setIdregistro(Integer idregistro) {
		this.idregistro = idregistro;
	}

	public String getTiporegistro() {
		return tiporegistro;
	}

	public void setTiporegistro(String tiporegistro) {
		this.tiporegistro = tiporegistro;
	}

	public Integer getIdproductoProductos() {
		return idproductoProductos;
	}

	public void setIdproductoProductos(Integer idproductoProductos) {
		this.idproductoProductos = idproductoProductos;
	}

	public Integer getIdusuarioRegistrador() {
		return idusuarioRegistrador;
	}

	public void setIdusuarioRegistrador(Integer idusuarioRegistrador) {
		this.idusuarioRegistrador = idusuarioRegistrador;
	}

	public Integer getIdusuarioModificador() {
		return idusuarioModificador;
	}

	public void setIdusuarioModificador(Integer idusuarioModificador) {
		this.idusuarioModificador = idusuarioModificador;
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
