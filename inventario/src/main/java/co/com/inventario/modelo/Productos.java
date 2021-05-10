package co.com.inventario.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "productos", schema = "public")
public class Productos implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer idproducto;
    @NotNull
    private Date fechacreacion;
    private Date fechamodificacion;
    @NotNull
    @NotEmpty
    @Size(max = 150)
    private String nombreproducto;
    private List<Resgistromercancia> resgistromercancias = new ArrayList<>(0);

    public Productos() {
    	//Inicializacion
    }

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productos_idproducto_seq")
	@SequenceGenerator(name="productos_idproducto_seq", sequenceName = "productos_idproducto_seq", allocationSize = 1)
    @Column(name = "idproducto", unique = true, nullable = false)
    public Integer getIdproducto() {
        return this.idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    @Column(name = "fechacreacion", nullable = false)
    public Date getFechacreacion() {
        return this.fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    @Column(name = "fechamodificacion")
    public Date getFechamodificacion() {
        return this.fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    @Column(name = "nombreproducto", nullable = false)
    public String getNombreproducto() {
        return this.nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productos")
    public List<Resgistromercancia> getResgistromercancias() {
        return this.resgistromercancias;
    }

    public void setResgistromercancias(
        List<Resgistromercancia> resgistromercancias) {
        this.resgistromercancias = resgistromercancias;
    }
}
