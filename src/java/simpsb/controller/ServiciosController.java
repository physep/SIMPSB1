package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.ServiciosFacadeLocal;
import simpsb.entidades.Servicios;

@Named
@RequestScoped
public class ServiciosController {

    @EJB
    private ServiciosFacadeLocal serviciosFacadeLocal;
    private Servicios servicios;

    @PostConstruct
    public void init() {
        servicios = new Servicios();
    }

    public ServiciosFacadeLocal getServiciosFacadeLocal() {
        return serviciosFacadeLocal;
    }

    public void setServiciosFacadeLocal(ServiciosFacadeLocal serviciosFacadeLocal) {
        this.serviciosFacadeLocal = serviciosFacadeLocal;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public List<Servicios> listarServicios() {
        List<Servicios> listServicios = null;
        try {
            listServicios = serviciosFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listServicios;
    }

    public void eliminarServicios(Servicios servicio) {
        try {
            serviciosFacadeLocal.remove(servicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Eliminacion exitosa"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "No conexion"));
            e.printStackTrace();
        }
    }

    public void registrarServicios() {
        try {
            serviciosFacadeLocal.create(servicios);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Eliminacion exitosa"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarServicio.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "No conexion"));
            e.printStackTrace();
        }
    }

    public void actualizarServicios() {
        try {
            serviciosFacadeLocal.edit(servicios);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Actulizacion exitosa"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarServicio.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "No conexion"));
            e.printStackTrace();
        }

    }

    public String consultarServicio(Servicios se) {
        try {
           servicios = serviciosFacadeLocal.find(se.getIdServicio());
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Consulta exitosa"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "No conexion"));
            e.printStackTrace();
        }
        return "modificarServicio";

    }
}
