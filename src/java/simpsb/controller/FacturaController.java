package simpsb.controller;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
    private ServiciosFacadeLocal serviciosFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    
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
        usuario = new Usuario();
        servicios=new Servicios();
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

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
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
        try {
            factura.setIdCita(citas);
            factura.setIdDetalleFactura(detalle);
            detalle.setIva(16);
            detallefacturaFacadeLocal.create(detalle);
            facturaFacadeLocal.create(factura);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente su factura"));
             FacesContext.getCurrentInstance().getExternalContext().redirect("consultarFactura.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al generar su factura"));
        }
        
    }

    public List<Factura> listarFactura() {
        List<Factura> listFactura = null;
        List<Factura> listDetalle = null;
        try {
            listFactura = facturaFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFactura;
    }
    public String consultarUsuariosFa(Citas ct) {
        try {
           citas = citasFacadeLocal.find(ct.getIdCita());
            servicios = citas.getIdServicio();
            empleado = citas.getIdEmpleado();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Funciona correcto"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error"));
        }
        return "crearFactura";
    }
    
    public String consultarFactura(Factura factu) {
        try {
            factura = facturaFacadeLocal.find(factu.getIdFactura());
            detalle= detallefacturaFacadeLocal.find(factu.getIdDetalleFactura());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Correcto"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al modificar su factura"));
        }
        return "modificarFactura";
    }
    
    public List<Usuario> listarUsuarios() {
        List<Usuario> listUsu = null;
        try {
            listUsu = usuarioFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUsu;
    }
    public void modificarFactura() {
        try {
            facturaFacadeLocal.edit(factura);
            detallefacturaFacadeLocal.edit(detalle);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha generado exitosamente su factura"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarFactura.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al modificar su factura"));
        }
    }
    public List<Citas> listarCitas() {
        List<Citas> listCitas = null;
        try {
            listCitas = citasFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCitas;
    }

    public String consultarFacturaDos(Citas ct) {
        try {
            citas = citasFacadeLocal.find(ct.getIdCita());
            servicios = citas.getIdServicio();
            empleado = citas.getIdEmpleado();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Correcto"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al modificar su cita"));
        }
        return "crearFactura";
    }
    
    
    
    
    public void genenarPDF(ActionEvent actionEvent) {
        //Genero un Hash Map para los parametros del reporte
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idComision",1);
        

        //Genero la lista para los Fields del reporte
        listarUsuarios();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listCitas);

        //Traer la ruta del Jasper
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/");
        try {
            //Generar el Reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(ruta + "/Prueba.jasper", parametros, beanCollectionDataSource);
            
            //Con estas lineas mi navegador puede leer el PDF y lo puede descargar
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=ReporteFactura.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
   
    
    
    

