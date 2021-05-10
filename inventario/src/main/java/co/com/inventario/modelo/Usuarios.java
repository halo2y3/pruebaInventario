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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "usuarios", schema = "public")
public class Usuarios implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Integer idusuario;
    @NotNull
    private Cargos cargos;
    @NotNull
    private Integer edad;
    @NotNull
    private Date fechaingreso;
    @NotNull
    @NotEmpty
    @Size(max = 200)
    private String nombreusuario;
    private List<Resgistromercancia> resgistromercanciasForUsuarioidmodificador = new ArrayList<>(0);
    private List<Resgistromercancia> resgistromercanciasForUsuarioidregistrador = new ArrayList<>(0);

    public Usuarios() {
    	//Inicializacion
    }

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_idusuario_seq")
	@SequenceGenerator(name="usuarios_idusuario_seq", sequenceName = "usuarios_idusuario_seq", allocationSize = 1)
    @Column(name = "idusuario", unique = true, nullable = false)
    public Integer getIdusuario() {
        return this.idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargosidcargo")
    public Cargos getCargos() {
        return this.cargos;
    }

    public void setCargos(Cargos cargos) {
        this.cargos = cargos;
    }

    @Column(name = "edad", nullable = false)
    public Integer getEdad() {
        return this.edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Column(name = "fechaingreso", nullable = false)
    public Date getFechaingreso() {
        return this.fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    @Column(name = "nombreusuario", nullable = false)
    public String getNombreusuario() {
        return this.nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioidmodificador")
    public List<Resgistromercancia> getResgistromercanciasForUsuarioidmodificador() {
        return this.resgistromercanciasForUsuarioidmodificador;
    }

    public void setResgistromercanciasForUsuarioidmodificador(
        List<Resgistromercancia> resgistromercanciasForUsuarioidmodificador) {
        this.resgistromercanciasForUsuarioidmodificador = resgistromercanciasForUsuarioidmodificador;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariosByUsuarioidregistrador")
    public List<Resgistromercancia> getResgistromercanciasForUsuarioidregistrador() {
        return this.resgistromercanciasForUsuarioidregistrador;
    }

    public void setResgistromercanciasForUsuarioidregistrador(
        List<Resgistromercancia> resgistromercanciasForUsuarioidregistrador) {
        this.resgistromercanciasForUsuarioidregistrador = resgistromercanciasForUsuarioidregistrador;
    }
}
