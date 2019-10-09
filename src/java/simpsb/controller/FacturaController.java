package simpsb.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.*;
import simpsb.entidades.*;
import javax.faces.bean.ManagedBean;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dateAndTimeconverter")
@ManagedBean
@Named
@RequestScoped
public class FacturaController {
    @EJB
    private FacturaFacadeLocal facturaFacadeLocal;
    @EJB
    private CitasFacadeLocal citasFacadeLocal;
    @EJB
    private DetallefacturaFacadeLocal detallefacturaFacadeLocal;
    @EJB
    private ClienteFacadeLocal clienteFacadeLocal;
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuariosFacadeLocal;
    @EJB
    private ServiciosFacadeLocal serviciosFacadeLocal;
    
    
    private Citas citas;
    private Detallefactura detalle;
    private Factura factura;
    private Cliente cliente;
    private Empleado empleado;
    private Usuario usuario;
    private Servicios servicios;
   
    
    
    
    private List<Factura> listFactura;
    private List<Citas> listCitas;
    private List<Detallefactura> listDetallefactura;
    private List<Cliente> listCliente;
    private List<Empleado> listEmpleado; 
     private List<Servicios> listServicios;
    

    @PostConstruct
    public void init(){
        citas= new Citas();
        detalle= new Detallefactura();
        factura= new Factura();
        listCitas= citasFacadeLocal.findAll();
        listDetallefactura= detallefacturaFacadeLocal.findAll();
        listCliente = clienteFacadeLocal.findAll();
        listEmpleado= empleadoFacadeLocal.findAll();
        listServicios= serviciosFacadeLocal.findAll();
    }

    public FacturaController() {
    }

    public FacturaFacadeLocal getFacturaFacadeLocal() {
        return facturaFacadeLocal;
    }

    public void setFacturaFacadeLocal(FacturaFacadeLocal facturaFacadeLocal) {
        this.facturaFacadeLocal = facturaFacadeLocal;
    }

    public CitasFacadeLocal getCitasFacadeLocal() {
        return citasFacadeLocal;
    }

    public void setCitasFacadeLocal(CitasFacadeLocal citasFacadeLocal) {
        this.citasFacadeLocal = citasFacadeLocal;
    }

    public DetallefacturaFacadeLocal getDetallefacturaFacadeLocal() {
        return detallefacturaFacadeLocal;
    }

    public void setDetallefacturaFacadeLocal(DetallefacturaFacadeLocal detallefacturaFacadeLocal) {
        this.detallefacturaFacadeLocal = detallefacturaFacadeLocal;
    }

    public ClienteFacadeLocal getClienteFacadeLocal() {
        return clienteFacadeLocal;
    }

    public void setClienteFacadeLocal(ClienteFacadeLocal clienteFacadeLocal) {
        this.clienteFacadeLocal = clienteFacadeLocal;
    }

    public EmpleadoFacadeLocal getEmpleadoFacadeLocal() {
        return empleadoFacadeLocal;
    }

    public void setEmpleadoFacadeLocal(EmpleadoFacadeLocal empleadoFacadeLocal) {
        this.empleadoFacadeLocal = empleadoFacadeLocal;
    }

    public UsuarioFacadeLocal getUsuariosFacadeLocal() {
        return usuariosFacadeLocal;
    }

    public void setUsuariosFacadeLocal(UsuarioFacadeLocal usuariosFacadeLocal) {
        this.usuariosFacadeLocal = usuariosFacadeLocal;
    }

    public Citas getCitas() {
        return citas;
    }

    public void setCitas(Citas citas) {
        this.citas = citas;
    }

    public Detallefactura getDetalle() {
        return detalle;
    }

    public void setDetalle(Detallefactura detalle) {
        this.detalle = detalle;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public List<Factura> getListFactura() {
        return listFactura;
    }

    public void setListFactura(List<Factura> listFactura) {
        this.listFactura = listFactura;
    }

    public List<Citas> getListCitas() {
        return listCitas;
    }

    public void setListCitas(List<Citas> listCitas) {
        this.listCitas = listCitas;
    }

    public List<Detallefactura> getListDetallefactura() {
        return listDetallefactura;
    }

    public void setListDetallefactura(List<Detallefactura> listDetallefactura) {
        this.listDetallefactura = listDetallefactura;
    }

    public List<Cliente> getListCliente() {
        return listCliente;
    }

    public void setListUsuario(List<Cliente> listCliente) {
        this.listCliente = listCliente;
    }

    public List<Empleado> getListEmpleado() {
        return listEmpleado;
    }

    public void setListEmpleado(List<Empleado> listEmpleado) {
        this.listEmpleado = listEmpleado;
    }

    public ServiciosFacadeLocal getServiciosFacadeLocal() {
        return serviciosFacadeLocal;
    }

    public void setServiciosFacadeLocal(ServiciosFacadeLocal serviciosFacadeLocal) {
        this.serviciosFacadeLocal = serviciosFacadeLocal;
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
public void generarFactura(){
            Date fechaActual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");
        try {
            //Date fecha = formato.format(fechaActual);
            factura.setFecha(fechaActual);
            factura.setIdCita(citas);
            factura.setIdDetalleFactura(detalle);
            detallefacturaFacadeLocal.create(detalle);
            facturaFacadeLocal.create(factura);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente su factura"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al generar su factura"));
        }
    }

    public List<Factura> listarFactura() {
        List<Factura> listFactura = null;
        try {
            listFactura = facturaFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFactura;
    }
    
    public String consultarFactura(Factura factu) {
        try {
            factura = facturaFacadeLocal.find(factu.getIdCita());
            detalle = factura.getIdDetalleFactura();
            citas = factura.getIdCita();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Correcto"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al modificar su cita"));
        }
        return "modificarFactura";
    }
    
    public void modificarFactura() {
        try {
            factura.setIdCita(citas);
            facturaFacadeLocal.edit(factura);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente su cita"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarFactura.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al modificar su cita"));
        }
    }
    
}
   
    
    
    

