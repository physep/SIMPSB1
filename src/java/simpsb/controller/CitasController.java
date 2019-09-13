package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.DAO.EmpleadoFacadeLocal;
import simpsb.DAO.ServiciosFacadeLocal;
import simpsb.dao.*;
import simpsb.entidades.*;

@Named
@RequestScoped

public class CitasController {
    @EJB
    private CitasFacadeLocal citasFacadeLocal;
    private Citas citas;
    
    @EJB 
    private ServiciosFacadeLocal serviciosFacadeLocal;
    private Servicios servicios;
    private List<Servicios> listServicios;
    
    @EJB 
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    private Empleado empleado;
    private List<Empleado> listEmpleados;
    
    
    @PostConstruct
    public void init(){
        citas = new Citas();
        servicios = new Servicios();
        listServicios = serviciosFacadeLocal.findAll();
        empleado = new Empleado();
        listEmpleados = empleadoFacadeLocal.findAll();
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public List<Servicios> getListServicios() {
        return listServicios;
    }

    public void setListServicios(List<Servicios> listServicios) {
        this.listServicios = listServicios;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getListEmpleados() {
        return listEmpleados;
    }

    public void setListEmpleados(List<Empleado> listEmpleados) {
        this.listEmpleados = listEmpleados;
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
