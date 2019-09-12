package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.CitasFacadeLocal;
import simpsb.entidades.Citas;

@Named
@RequestScoped
public class CitasController {
    @EJB
    private CitasFacadeLocal citasFacadeLocal;
    private Citas citas;
    
    @PostConstruct
    public void init(){
        citas = new Citas();
    }

    public Citas getCitas() {
        return citas;
    }

    public void setCitas(Citas citas) {
        this.citas = citas;
    }
    
    public void generarCita(){
        try {
            citasFacadeLocal.create(citas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente su cita"));
        } catch (Exception e) {
            citasFacadeLocal.create(citas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al generar su cita"));            
        }
    }
    
    //MÉTODO PARA LISTAR
    public List<Citas> listarCitas(){
        List<Citas> listCitas = null;
        try {
            listCitas = citasFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCitas;
    }
}
