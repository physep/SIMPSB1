package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.DAO.EmpleadoFacadeLocal;
import simpsb.entidades.Empleado;

@Named
@RequestScoped
public class EmpleadoController {
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    private Empleado empleado;
    
    
    @PostConstruct
    public void init(){
        empleado = new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    
    //MÉTODO PARA LISTAR
    public List<Empleado> listarEmpleados(){
        List<Empleado> listEmpleados = null;
        try {
            listEmpleados = empleadoFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listEmpleados;
    }
}
