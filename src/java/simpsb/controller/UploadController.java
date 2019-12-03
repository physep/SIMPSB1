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
 * @author Leonardo Lara
 */
@Named
@RequestScoped
public class UploadController {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;

    private Part file;
    private String nombre;
    private String pathReal;

    private List<Usuario> listArchivos;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public UploadController() {
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<Usuario> getListArchivos() {
        return listArchivos;
    }

    public void setListArchivos(List<Usuario> listArchivos) {
        this.listArchivos = listArchivos;
    }

    public String subirImagenes() {
        //Obtengo la ruta de la carpeta
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("FotosPerfil");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\FotosPerfil\\";
        
        System.out.println("Hola "+path);
        try {
            this.nombre = file.getSubmittedFileName();
            path = path + this.nombre;
            pathReal = "/FotosPerfil/" + this.nombre;

            InputStream in = file.getInputStream();
            File f = new File(path);
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);

            byte[] data = new byte[in.available()];
            in.read(data);
            out.write(data);

            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "cargado?faces-redirect=true";
    }

}
