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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author LeonardoLara
 */
@Entity
@Table(name = "fotosPerfil")
@NamedQueries({
    @NamedQuery(name = "FotosPerfil.findAll", query = "SELECT f FROM FotosPerfil f")
    , @NamedQuery(name = "FotosPerfil.findByIdfotosPerfil", query = "SELECT f FROM FotosPerfil f WHERE f.idfotosPerfil = :idfotosPerfil")
    , @NamedQuery(name = "FotosPerfil.findByNombreFoto", query = "SELECT f FROM FotosPerfil f WHERE f.nombreFoto = :nombreFoto")
    , @NamedQuery(name = "FotosPerfil.findByRutaFoto", query = "SELECT f FROM FotosPerfil f WHERE f.rutaFoto = :rutaFoto")
    , @NamedQuery(name = "FotosPerfil.findByTipoFoto", query = "SELECT f FROM FotosPerfil f WHERE f.tipoFoto = :tipoFoto")})
public class FotosPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idfotosPerfil")
    private Integer idfotosPerfil;
    @Size(max = 45)
    @Column(name = "nombreFoto")
    private String nombreFoto;
    @Size(max = 200)
    @Column(name = "rutaFoto")
    private String rutaFoto;
    @Size(max = 45)
    @Column(name = "tipoFoto")
    private String tipoFoto;

    public FotosPerfil() {
    }

    public FotosPerfil(Integer idfotosPerfil) {
        this.idfotosPerfil = idfotosPerfil;
    }

    public Integer getIdfotosPerfil() {
        return idfotosPerfil;
    }

    public void setIdfotosPerfil(Integer idfotosPerfil) {
        this.idfotosPerfil = idfotosPerfil;
    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(String tipoFoto) {
        this.tipoFoto = tipoFoto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfotosPerfil != null ? idfotosPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FotosPerfil)) {
            return false;
        }
        FotosPerfil other = (FotosPerfil) object;
        if ((this.idfotosPerfil == null && other.idfotosPerfil != null) || (this.idfotosPerfil != null && !this.idfotosPerfil.equals(other.idfotosPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "simpsb.entidades.FotosPerfil[ idfotosPerfil=" + idfotosPerfil + " ]";
    }
    
}
