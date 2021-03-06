/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.HorariotrabajoFacadeLocal;
import simpsb.entidades.Horariotrabajo;

@Named
@RequestScoped
public class HorariosController {
    @EJB
    private HorariotrabajoFacadeLocal HorariotrabajoFacadeLocal;
    private Horariotrabajo Horariotrabajo;
    
    @PostConstruct
    public void init(){
         Horariotrabajo = new Horariotrabajo();
    }
    


    public HorariotrabajoFacadeLocal getHorariotrabajoFacadeLocal() {
        return HorariotrabajoFacadeLocal;
    }

    public void setHorariotrabajoFacadeLocal(HorariotrabajoFacadeLocal HorariotrabajoFacadeLocal) {
        this.HorariotrabajoFacadeLocal = HorariotrabajoFacadeLocal;
    }

    public Horariotrabajo getHorariotrabajo() {
        return Horariotrabajo;
    }

    public void setHorariotrabajo(Horariotrabajo Horariotrabajo) {
        this.Horariotrabajo = Horariotrabajo;
    }
    
    public void registrarHorario() {
        try {
            HorariotrabajoFacadeLocal.create(Horariotrabajo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Registro exitoso"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarHorario.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "No se registro"));
            e.printStackTrace();
        }
    }
     public void eliminarHorariotrabajo(Horariotrabajo horariotrabajo) {
        try {
           HorariotrabajoFacadeLocal.remove(horariotrabajo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Eliminacion exitosa"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "No conexion"));
            e.printStackTrace();
        }
    }
      public void actualizarHorario() {

        try {
            HorariotrabajoFacadeLocal.edit(Horariotrabajo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Actulizacion exitosa"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarHorario.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "Parra Hijueputa"));
            e.printStackTrace();
        }

    }

    public String consultarHorariotrabajo(Horariotrabajo hor) {
        try {
           Horariotrabajo= HorariotrabajoFacadeLocal.find(hor.getIdHorarioTrabajo());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso: ", "Consulta exitosa"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "Parra Hijueputa"));
            e.printStackTrace();
        }
        return "modificarHorario";

    }
    
    public List<Horariotrabajo> listarHorariotrabajo() {
        List<Horariotrabajo> listHorariotrabajo = null;
        try {
            listHorariotrabajo = HorariotrabajoFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHorariotrabajo;
    }
}
