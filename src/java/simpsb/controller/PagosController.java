
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
public class PagosController {
    
    @EJB
    private ComisionesFacadeLocal comisionesFacadeLocal;
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private FacturaFacadeLocal facturaFacadeLocal;
    
    private Comisiones comisiones;
    private Empleado empleado;
    private Usuario usuario;
    private Factura factura;
    
    private List<Empleado> listEmpleado;
    private List<Factura> listFactura;
    
    @PostConstruct
    public void init(){
        comisiones = new Comisiones();
        empleado = new Empleado();
        usuario = new Usuario();
        factura = new Factura();
        listEmpleado = empleadoFacadeLocal.findAll();
        listFactura = facturaFacadeLocal.findAll();
    }

    public ComisionesFacadeLocal getComisionesFacadeLocal() {
        return comisionesFacadeLocal;
    }

    public void setComisionesFacadeLocal(ComisionesFacadeLocal comisionesFacadeLocal) {
        this.comisionesFacadeLocal = comisionesFacadeLocal;
    }

    public EmpleadoFacadeLocal getEmpleadoFacadeLocal() {
        return empleadoFacadeLocal;
    }

    public void setEmpleadoFacadeLocal(EmpleadoFacadeLocal empleadoFacadeLocal) {
        this.empleadoFacadeLocal = empleadoFacadeLocal;
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public FacturaFacadeLocal getFacturaFacadeLocal() {
        return facturaFacadeLocal;
    }

    public void setFacturaFacadeLocal(FacturaFacadeLocal facturaFacadeLocal) {
        this.facturaFacadeLocal = facturaFacadeLocal;
    }

    public Comisiones getComisiones() {
        return comisiones;
    }

    public void setComisiones(Comisiones comisiones) {
        this.comisiones = comisiones;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Empleado> getListEmpleado() {
        return listEmpleado;
    }

    public void setListEmpleado(List<Empleado> listEmpleado) {
        this.listEmpleado = listEmpleado;
    }

    public List<Factura> getListFactura() {
        return listFactura;
    }

    public void setListFactura(List<Factura> listFactura) {
        this.listFactura = listFactura;
    }
    
    public void generarPago() {
        try {
            comisiones.setIdEmpleado(empleado);
            comisiones.setIdFactura(factura);
            comisionesFacadeLocal.create(comisiones);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente su pago"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarPago.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al generar su pago"));
        }
    }

    public void eliminarPagos() {
        try {
            comisionesFacadeLocal.remove(comisiones);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", ""));
        }
    }

    public List<Comisiones> listarPagos() {
        List<Comisiones> listPagos = null;
        try {
            listPagos = comisionesFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPagos;
    }
    
}
