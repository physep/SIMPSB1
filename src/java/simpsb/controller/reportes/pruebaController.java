/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.controller.reportes;
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
public class pruebaController {
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
    @EJB
    private ComisionesFacadeLocal comisionesFacadeLocal;
    
    private Citas citas;
    private Detallefactura detalle;
    private Factura factura;
    private Cliente cliente;
    private Empleado empleado;
    private Usuario usuario;
    private Servicios servicios;
    private Comisiones comisiones;
    
    
    
    private List<Factura> listFactura;
    private List<Citas> listCitas;
    private List<Detallefactura> listDetallefactura;
    private List<Cliente> listCliente;
    private List<Empleado> listEmpleado; 
    private List<Servicios> listServicios;
    private List<Usuario> listUsuarios;
    private List<Comisiones> listComisiones;
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
        listUsuarios=usuarioFacadeLocal.findAll();
        listComisiones=comisionesFacadeLocal.findAll();
    }


    public List<Comisiones> listarComisiones() {
        listComisiones = comisionesFacadeLocal.findAll();
        return listComisiones;
    }
     public List<Usuario> listarUsuarios() {
        listUsuarios = usuarioFacadeLocal.findAll();
        return listUsuarios;
    }
    
     public List<Empleado> listarEmpleados() {
        listEmpleado = empleadoFacadeLocal.findAll();
        return listEmpleado; 
    }
     
     public void metodos(){
         listarComisiones();
        listarUsuarios();
        listarEmpleados();
     }
    public void genenarPDF(ActionEvent actionEvent) {
        //Genero un Hash Map para los parametros del reporte
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idComision",1);
        

        //Genero la lista para los Fields del reporte
        listarComisiones();
        listarUsuarios();
        listarEmpleados();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listUsuarios);
        
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
