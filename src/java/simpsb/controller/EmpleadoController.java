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
public class EmpleadoController {
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;
    
    @EJB
    private CargosFacadeLocal cargosFacadeLocal;
    private Cargos cargos;
    private List<Cargos> listCargos;

    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    private Empleado empleado;

    @EJB
    private HorariotrabajoFacadeLocal horariotrabajoFacadeLocal;
    private Horariotrabajo horariotrabajo;
    private List<Horariotrabajo> listHorario;

    @EJB
    private DiadescansoFacadeLocal diadescansoFacadeLocal;
    private Diadescanso diadescanso;
    private List<Diadescanso> listDiaD;
    
    @PostConstruct
    public void init(){
        empleado = new Empleado();
        cargos = new Cargos();
        diadescanso = new Diadescanso();
        horariotrabajo = new Horariotrabajo();
        usuario = new Usuario();
        listDiaD = diadescansoFacadeLocal.findAll();
        listCargos = cargosFacadeLocal.findAll();
        listHorario = horariotrabajoFacadeLocal.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Diadescanso getDiadescanso() {
        return diadescanso;
    }

    public void setDiadescanso(Diadescanso diadescanso) {
        this.diadescanso = diadescanso;
    }

    public List<Diadescanso> getListDiaD() {
        return listDiaD;
    }

    public void setListDiaD(List<Diadescanso> listDiaD) {
        this.listDiaD = listDiaD;
    }

    public Horariotrabajo getHorariotrabajo() {
        return horariotrabajo;
    }

    public void setHorariotrabajo(Horariotrabajo horariotrabajo) {
        this.horariotrabajo = horariotrabajo;
    }

    public List<Horariotrabajo> getListHorario() {
        return listHorario;
    }

    public void setListHorario(List<Horariotrabajo> listHorario) {
        this.listHorario = listHorario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cargos getCargos() {
        return cargos;
    }

    public void setCargos(Cargos cargos) {
        this.cargos = cargos;
    }

    public List<Cargos> getListCargos() {
        return listCargos;
    }

    public void setListCargos(List<Cargos> listCargos) {
        this.listCargos = listCargos;
    }

     public void asignarRol() {
        try {
            empleado.setIdUsuario(usuario);
            empleado.setIdCargo(cargos);
            empleado.setIdDiaDescanso(diadescanso);
            empleado.setIdHorarioTrabajo(horariotrabajo);
            empleadoFacadeLocal.create(empleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha asignado correctamente el rol"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ha ocurrido un error"));

        }
    }
}

