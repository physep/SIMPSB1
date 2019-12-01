package simpsb.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import simpsb.dao.*;
import simpsb.entidades.*;

@Named
@RequestScoped
public class CitasController {
    
    MailController mailC = new MailController();
    @Inject    
    Utils util;
    
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
    @EJB
    private EstadoFacadeLocal estadoFacadeLocal;
    
    private Citas citas;
    private Empleado empleado;
    private Servicios servicios;
    private Usuario usuario;
    private Cliente cliente;
    private Estado estado;
    
    private List<Servicios> listServicios;
    private List<Empleado> listEmpleados;
    
    @PostConstruct
    public void init() {
        citas = new Citas();
        servicios = new Servicios();
        empleado = new Empleado();
        estado = new Estado();
        cliente = new Cliente();
        listServicios = serviciosFacadeLocal.findAll();
        listEmpleados = empleadoFacadeLocal.findAll();
        validarEstado();
    }

    //GETTERS Y SETTERS CONTROLADOR
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
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

    //FECHAS 
    Calendar fecha = Calendar.getInstance();
    int dia = fecha.get(Calendar.DATE);
    int mes = fecha.get(Calendar.MONTH) + 1;
    int año = fecha.get(Calendar.YEAR);

    //GETTERS Y SETTERS FECHA
    public int getDia() {
        return dia;
    }
    
    public void setDia(int dia) {
        this.dia = dia;
    }
    
    public int getMes() {
        return mes;
    }
    
    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public int getAño() {
        return año;
    }
    
    public void setAño(int año) {
        this.año = año;
    }
    
    public void generarCita() {
        
        Usuario user = null;
        Cliente cl = null;
        try {
//            user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSession");
//            cl = clienteFacadeLocal.find(user.getIdUsuario());
            cliente.setIdCliente(1);
            citas.setIdCliente(cliente);
            citas.setIdEmpleado(empleado);
            citas.setIdServicio(servicios);
            estado.setIdEstado(3);
            citas.setEstadoFK(estado);
            citasFacadeLocal.create(citas);
            mailC.citas();
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
            e.printStackTrace();
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

    //METODO PARA VALIDAR EL ESTADO DE LA CITA
    private void validarEstado() {
        try {
            //CREO UNA LISTA PARA TRAER TODAS LAS FECHAS

            List<Citas> listaCitas = null;
            listaCitas = citasFacadeLocal.findAll();

            //CONVIERTO LAS FECHAS A STRING
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            
            for (Citas ct : listaCitas) {
                String fechaString = sdf.format(ct.getFecha());
                String fechaActual = sdf.format(new Date());
                //CONVIERTO LAS FECHAS A INT PARA PODER COMPARARLAS
                int fechaInt = Integer.parseInt(fechaString);
                int fechaActualInt = Integer.parseInt(fechaActual);
                //VERIFICO QUE LA LISTA DE LAS CITAS NO ESTÉ VACIA
                if (listaCitas != null) {
                    //HAGO UNA CONDICION PARA CAMBIAR EL ESTADO DE LA CITA A VENCIDA
                    if (fechaInt < fechaActualInt) {
                        estado.setIdEstado(2);
                        ct.setEstadoFK(estado);
                        citasFacadeLocal.edit(ct);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "La lista de citas esta vacia"));
        }
    }

    //MÉTODOS ESPECIALES PARA EL PERFIL CLIENTE
    public void listarCitasCl() {
        
    }
    
}
