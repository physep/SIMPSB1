/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author LeonardoLara
 */
@Entity
@Table(name = "horariotrabajo")
@NamedQueries({
    @NamedQuery(name = "Horariotrabajo.findAll", query = "SELECT h FROM Horariotrabajo h")
    , @NamedQuery(name = "Horariotrabajo.findByIdHorarioTrabajo", query = "SELECT h FROM Horariotrabajo h WHERE h.idHorarioTrabajo = :idHorarioTrabajo")
    , @NamedQuery(name = "Horariotrabajo.findByHoraInicio", query = "SELECT h FROM Horariotrabajo h WHERE h.horaInicio = :horaInicio")
    , @NamedQuery(name = "Horariotrabajo.findByHoraFin", query = "SELECT h FROM Horariotrabajo h WHERE h.horaFin = :horaFin")})
public class Horariotrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHorarioTrabajo")
    private Integer idHorarioTrabajo;
    @Column(name = "horaInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "horaFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @OneToMany(mappedBy = "idHorarioTrabajo")
    private List<Empleado> empleadoList;

    public Horariotrabajo() {
    }

    public Horariotrabajo(Integer idHorarioTrabajo) {
        this.idHorarioTrabajo = idHorarioTrabajo;
    }

    public Integer getIdHorarioTrabajo() {
        return idHorarioTrabajo;
    }

    public void setIdHorarioTrabajo(Integer idHorarioTrabajo) {
        this.idHorarioTrabajo = idHorarioTrabajo;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorarioTrabajo != null ? idHorarioTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horariotrabajo)) {
            return false;
        }
        Horariotrabajo other = (Horariotrabajo) object;
        if ((this.idHorarioTrabajo == null && other.idHorarioTrabajo != null) || (this.idHorarioTrabajo != null && !this.idHorarioTrabajo.equals(other.idHorarioTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "simpsb.entidades.Horariotrabajo[ idHorarioTrabajo=" + idHorarioTrabajo + " ]";
    }
    
}
