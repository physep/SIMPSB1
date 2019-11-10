/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SebastianParra
 */
@Entity
@Table(name = "comisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comisiones.findAll", query = "SELECT c FROM Comisiones c")
    , @NamedQuery(name = "Comisiones.findByIdComisiones", query = "SELECT c FROM Comisiones c WHERE c.idComisiones = :idComisiones")
    , @NamedQuery(name = "Comisiones.findByValor", query = "SELECT c FROM Comisiones c WHERE c.valor = :valor")})
public class Comisiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComisiones")
    private Integer idComisiones;
    @Column(name = "valor")
    private Integer valor;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne
    private Empleado idEmpleado;
    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura")
    @ManyToOne
    private Factura idFactura;

    public Comisiones() {
    }

    public Comisiones(Integer idComisiones) {
        this.idComisiones = idComisiones;
    }

    public Integer getIdComisiones() {
        return idComisiones;
    }

    public void setIdComisiones(Integer idComisiones) {
        this.idComisiones = idComisiones;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComisiones != null ? idComisiones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comisiones)) {
            return false;
        }
        Comisiones other = (Comisiones) object;
        if ((this.idComisiones == null && other.idComisiones != null) || (this.idComisiones != null && !this.idComisiones.equals(other.idComisiones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "simpsb.entidades.Comisiones[ idComisiones=" + idComisiones + " ]";
    }
    
}
