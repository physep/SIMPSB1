package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.*;
import simpsb.entidades.*;

@Named
@RequestScoped
public class CitasController {

    @EJB
    private CitasFacadeLocal citasFacadeLocal;
    @EJB
    private ServiciosFacadeLocal serviciosFacadeLocal;
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    private ClienteFacadeLocal clienteFacadeLocal;

    private Citas citas;
    private Empleado empleado;
    private Servicios servicios;
    private Usuario usuario;
    private Cliente cliente;

    private List<Servicios> listServicios;
    private List<Empleado> listEmpleados;

    @PostConstruct
    public void init() {
        citas = new Citas();
        servicios = new Servicios();
        empleado = new Empleado();
        listServicios = serviciosFacadeLocal.findAll();
        listEmpleados = empleadoFacadeLocal.findAll();
    }

    public Citas getCitas() {
        return citas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCitas(Citas citas) {
        this.citas = citas;
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

    public void generarCita() {
        Usuario us = null;
        Cliente cl = null;
        try {
            us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            cl = clienteFacadeLocal.find(us.getIdUsuario());
            citas.setIdCliente(cl);
            citas.setIdEmpleado(empleado);
            citas.setIdServicio(servicios);
            citasFacadeLocal.create(citas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente la cita"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarCita.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al generar la cita"));
        }
    }

    public void eliminarCita(Citas cita) {
        try {
            citasFacadeLocal.remove(cita);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha eliminado exitosamente la cita"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al eliminar la cita"));
        }
    }

    //MÉTODO PARA LISTAR
    public List<Citas> listarCitas() {
        List<Citas> listCitas = null;
        try {
            listCitas = citasFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCitas;
    }

    //METODO PARA CONSULTAR LA CITA
    public String consultarCita(Citas ct) {
        try {
            citas = citasFacadeLocal.find(ct.getIdCita());
            servicios = citas.getIdServicio();
            empleado = citas.getIdEmpleado();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Correcto"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al modificar su cita"));
        }
        return "modificarCita";

    }

    //METODO PARA MODIFICAR LA CITA
    public void modificarCita() {
        try {
            citas.setIdEmpleado(empleado);
            citas.setIdServicio(servicios);
            citasFacadeLocal.edit(citas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente su cita"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarCita.xhtml");
        } catch (Exception e) {
            citasFacadeLocal.edit(citas);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al modificar su cita"));
        }
    }
}
