package co.com.inventario.modelo;

import java.util.ArrayList;
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
@Table(name = "cargos", schema = "public")
public class Cargos implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer idcargo;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String nombrecargo;
    private List<Usuarios> usuarioses = new ArrayList<>(0);

    public Cargos() {
    	//Inicializacion
    }


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargos_idcargo_seq")
	@SequenceGenerator(name="cargos_idcargo_seq", sequenceName = "cargos_idcargo_seq", allocationSize = 1)
    @Column(name = "idcargo", unique = true, nullable = false)
    public Integer getIdcargo() {
        return this.idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    @Column(name = "nombrecargo", nullable = false)
    public String getNombrecargo() {
        return this.nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargos")
    public List<Usuarios> getUsuarioses() {
        return this.usuarioses;
    }

    public void setUsuarioses(List<Usuarios> usuarioses) {
        this.usuarioses = usuarioses;
    }
}
