/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Leonardo Lara
 */
@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "Empleado.findByDescripcion", query = "SELECT e FROM Empleado e WHERE e.descripcion = :descripcion")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmpleado")
    private Integer idEmpleado;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idEmpleado")
    private List<Citas> citasList;
    @OneToMany(mappedBy = "idEmpleado")
    private List<Comisiones> comisionesList;
    @JoinColumn(name = "idHorarioTrabajo", referencedColumnName = "idHorarioTrabajo")
    @ManyToOne
    private Horariotrabajo idHorarioTrabajo;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "idDiaDescanso", referencedColumnName = "idDiaDescanso")
    @ManyToOne
    private Diadescanso idDiaDescanso;
    @JoinColumn(name = "idCargo", referencedColumnName = "idCargo")
    @ManyToOne
    private Cargos idCargo;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Citas> getCitasList() {
        return citasList;
    }

    public void setCitasList(List<Citas> citasList) {
        this.citasList = citasList;
    }

    public List<Comisiones> getComisionesList() {
        return comisionesList;
    }

    public void setComisionesList(List<Comisiones> comisionesList) {
        this.comisionesList = comisionesList;
    }

    public Horariotrabajo getIdHorarioTrabajo() {
        return idHorarioTrabajo;
    }

    public void setIdHorarioTrabajo(Horariotrabajo idHorarioTrabajo) {
        this.idHorarioTrabajo = idHorarioTrabajo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Diadescanso getIdDiaDescanso() {
        return idDiaDescanso;
    }

    public void setIdDiaDescanso(Diadescanso idDiaDescanso) {
        this.idDiaDescanso = idDiaDescanso;
    }

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "simpsb.entidades.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
