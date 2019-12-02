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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author SebastianParra
 */
@Entity
@Table(name = "diadescanso")
@NamedQueries({
    @NamedQuery(name = "Diadescanso.findAll", query = "SELECT d FROM Diadescanso d")
    , @NamedQuery(name = "Diadescanso.findByIdDiaDescanso", query = "SELECT d FROM Diadescanso d WHERE d.idDiaDescanso = :idDiaDescanso")
    , @NamedQuery(name = "Diadescanso.findByDia", query = "SELECT d FROM Diadescanso d WHERE d.dia = :dia")})
public class Diadescanso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDiaDescanso")
    private Integer idDiaDescanso;
    @Column(name = "dia")
    private String dia;
    @OneToMany(mappedBy = "idDiaDescanso")
    private List<Empleado> empleadoList;

    public Diadescanso() {
    }

    public Diadescanso(Integer idDiaDescanso) {
        this.idDiaDescanso = idDiaDescanso;
    }

    public Integer getIdDiaDescanso() {
        return idDiaDescanso;
    }

    public void setIdDiaDescanso(Integer idDiaDescanso) {
        this.idDiaDescanso = idDiaDescanso;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
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
        hash += (idDiaDescanso != null ? idDiaDescanso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diadescanso)) {
            return false;
        }
        Diadescanso other = (Diadescanso) object;
        if ((this.idDiaDescanso == null && other.idDiaDescanso != null) || (this.idDiaDescanso != null && !this.idDiaDescanso.equals(other.idDiaDescanso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "simpsb.entidades.Diadescanso[ idDiaDescanso=" + idDiaDescanso + " ]";
    }
    
}
