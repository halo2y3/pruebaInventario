package co.com.inventario.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
* @author Edwin Gonzalez
*
*/
@Entity
@Table(name = "resgistromercancia", schema = "public")
public class Resgistromercancia implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer idregistro;
    @NotNull
    private Productos productos;
    private Usuarios usuariosByUsuarioidmodificador;
    @NotNull
    private Usuarios usuariosByUsuarioidregistrador;
    @NotNull
    private Integer cantidad;
    @NotNull
    private Date fechaingresoegreso;
    private Date fechamodificacion;
    @NotNull
    private Date fecharegistro;
    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String tiporegistro;

    public Resgistromercancia() {
    	//Inicializacion
    }

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resgistromercancia_idregistro_seq")
	@SequenceGenerator(name="resgistromercancia_idregistro_seq", sequenceName = "resgistromercancia_idregistro_seq", allocationSize = 1)
    @Column(name = "idregistro", unique = true, nullable = false)
    public Integer getIdregistro() {
        return this.idregistro;
    }

    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoidproducto")
    public Productos getProductos() {
        return this.productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioidmodificador")
    public Usuarios getUsuariosByUsuarioidmodificador() {
        return this.usuariosByUsuarioidmodificador;
    }

    public void setUsuariosByUsuarioidmodificador(
        Usuarios usuariosByUsuarioidmodificador) {
        this.usuariosByUsuarioidmodificador = usuariosByUsuarioidmodificador;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioidregistrador")
    public Usuarios getUsuariosByUsuarioidregistrador() {
        return this.usuariosByUsuarioidregistrador;
    }

    public void setUsuariosByUsuarioidregistrador(
        Usuarios usuariosByUsuarioidregistrador) {
        this.usuariosByUsuarioidregistrador = usuariosByUsuarioidregistrador;
    }

    @Column(name = "cantidad", nullable = false)
    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "fechaingresoegreso", nullable = false)
    public Date getFechaingresoegreso() {
        return this.fechaingresoegreso;
    }

    public void setFechaingresoegreso(Date fechaingresoegreso) {
        this.fechaingresoegreso = fechaingresoegreso;
    }

    @Column(name = "fechamodificacion")
    public Date getFechamodificacion() {
        return this.fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    @Column(name = "fecharegistro", nullable = false)
    public Date getFecharegistro() {
        return this.fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    @Column(name = "tiporegistro", nullable = false)
    public String getTiporegistro() {
        return this.tiporegistro;
    }

    public void setTiporegistro(String tiporegistro) {
        this.tiporegistro = tiporegistro;
    }
}
