package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.DAO.ServiciosFacadeLocal;
import simpsb.entidades.Servicios;

@Named
@RequestScoped
public class ServiciosController {
    @EJB
    private ServiciosFacadeLocal serviciosFacadeLocal;
    private Servicios servicios;
    
    @PostConstruct
    public void init(){
        servicios = new Servicios();
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }
    
    
    //MÉTODO PARA LISTAR
    public List<Servicios> listarServicios(){
        List<Servicios> listServicios = null;
        try {
            listServicios = serviciosFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listServicios;
    }
}
