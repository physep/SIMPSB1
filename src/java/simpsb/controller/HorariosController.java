/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import simpsb.dao.HorariotrabajoFacadeLocal;
import simpsb.entidades.Horariotrabajo;

/**
 *
 * @author APRENDIZ
 */
public class HorariosController {
    @EJB
    private HorariotrabajoFacadeLocal HorariotrabajoFacadeLocal;
    private Horariotrabajo Horariotrabajo;@PostConstruct
    
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
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error: ", "No se registro"));
            e.printStackTrace();
        }
    }
}
