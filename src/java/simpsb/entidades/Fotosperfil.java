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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SebastianParra
 */
@Entity
@Table(name = "fotosperfil")
@NamedQueries({
    @NamedQuery(name = "Fotosperfil.findAll", query = "SELECT f FROM Fotosperfil f")
    , @NamedQuery(name = "Fotosperfil.findByIdFoto", query = "SELECT f FROM Fotosperfil f WHERE f.idFoto = :idFoto")
    , @NamedQuery(name = "Fotosperfil.findByFoto", query = "SELECT f FROM Fotosperfil f WHERE f.foto = :foto")
    , @NamedQuery(name = "Fotosperfil.findByRuta", query = "SELECT f FROM Fotosperfil f WHERE f.ruta = :ruta")
    , @NamedQuery(name = "Fotosperfil.findByTipo", query = "SELECT f FROM Fotosperfil f WHERE f.tipo = :tipo")})
public class Fotosperfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFoto")
    private Integer idFoto;
    @Column(name = "foto")
    private String foto;
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Fotosperfil() {
    }

    public Fotosperfil(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFoto != null ? idFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotosperfil)) {
            return false;
        }
        Fotosperfil other = (Fotosperfil) object;
        if ((this.idFoto == null && other.idFoto != null) || (this.idFoto != null && !this.idFoto.equals(other.idFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "simpsb.entidades.Fotosperfil[ idFoto=" + idFoto + " ]";
    }
    
}
