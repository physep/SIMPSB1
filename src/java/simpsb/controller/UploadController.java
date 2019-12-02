/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import simpsb.dao.*;
import simpsb.entidades.*;

/**
 *
 * @author LeonardoLara
 */
@Named
@RequestScoped
public class UploadController {
    
    @EJB
    private FotosPerfilFacadeLocal fotosFacadeLocal;
    private FotosPerfil fotosPerfil;

    private Part file;
    private String nombre;
    private String pathReal;

    private List<FotosPerfil> listFotos;

    @PostConstruct
    public void init() {
        fotosPerfil = new FotosPerfil();
    }

    public FotosPerfil getFotosPerfil() {
        return fotosPerfil;
    }

    public void setFotosPerfil(FotosPerfil fotosPerfil) {
        this.fotosPerfil = fotosPerfil;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public List<FotosPerfil> getListFotos() {
        return listFotos;
    }

    public void setListFotos(List<FotosPerfil> listFotos) {
        this.listFotos = listFotos;
    }

    public String subirArchivos() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("FotosPerfil");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\FotosPerfil\\";
        try {
            this.nombre = file.getSubmittedFileName();
            path = path + this.nombre;
            pathReal = "/SIMPSB1/FotosPerfil" + nombre;

            InputStream in = file.getInputStream();
            File f = new File(path);
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] data = new byte[in.available()];
            in.read(data);
            out.write(data);

            in.close();
            out.close();

            guardarBD();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "indexSupervisor?faces-redirect=true";
    }

    public void guardarBD() {
        try {
            fotosPerfil.setFoto(this.nombre);
            fotosPerfil.setRuta(this.pathReal);
            fotosPerfil.setTipo(this.file.getContentType());
            fotosFacadeLocal.create(fotosPerfil);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
