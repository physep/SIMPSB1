package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import simpsb.dao.ComisionesFacadeLocal;
import simpsb.dao.EmpleadoFacadeLocal;
import simpsb.dao.FacturaFacadeLocal;
import simpsb.entidades.Comisiones;
import simpsb.entidades.Empleado;
import simpsb.entidades.Factura;

@Named
@RequestScoped
public class PagosController {

    @EJB
    private ComisionesFacadeLocal comisionesFacadeLocal;
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    @EJB
    private FacturaFacadeLocal facturaFacadeLocal;
    
    
    private Comisiones comisiones;
    private Empleado empleado;
    private Factura factura;
    private List<Empleado> listEmpleados; 
    private List<Factura> listFacturas; 
            
    @PostConstruct
    public void init() {
        comisiones = new Comisiones();
        empleado = new Empleado();
        factura = new Factura();
        listEmpleados = empleadoFacadeLocal.findAll();
        listFacturas = facturaFacadeLocal.findAll();
    }

    public ComisionesFacadeLocal getComisionesFacadeLocal() {
        return comisionesFacadeLocal;
    }

    public void setComisionesFacadeLocal(ComisionesFacadeLocal comisionesFacadeLocal) {
        this.comisionesFacadeLocal = comisionesFacadeLocal;
    }

    public Comisiones getComisiones() {
        return comisiones;
    }

    public void setComisiones(Comisiones comisiones) {
        this.comisiones = comisiones;
    }

    public EmpleadoFacadeLocal getEmpleadoFacadeLocal() {
        return empleadoFacadeLocal;
    }

    public void setEmpleadoFacadeLocal(EmpleadoFacadeLocal empleadoFacadeLocal) {
        this.empleadoFacadeLocal = empleadoFacadeLocal;
    }

    public FacturaFacadeLocal getFacturaFacadeLocal() {
        return facturaFacadeLocal;
    }

    public void setFacturaFacadeLocal(FacturaFacadeLocal facturaFacadeLocal) {
        this.facturaFacadeLocal = facturaFacadeLocal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Empleado> getListEmpleados() {
        return listEmpleados;
    }

    public void setListEmpleados(List<Empleado> listEmpleados) {
        this.listEmpleados = listEmpleados;
    }

    public List<Factura> getListFacturas() {
        return listFacturas;
    }

    public void setListFacturas(List<Factura> listFacturas) {
        this.listFacturas = listFacturas;
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

    public void registrarPagos(){
        try {
            comisionesFacadeLocal.create(comisiones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarPagos(Comisiones comisiones) {
        try {
            comisionesFacadeLocal.remove(comisiones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
