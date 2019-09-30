/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class Languages implements Serializable{
    
    private Locale idiomaSeleccionado;
    private List<Locale> idiomasSoportados;
    
    @PostConstruct
    public void init (){
        FacesContext fc = FacesContext.getCurrentInstance();
        idiomaSeleccionado = new Locale("es");
        idiomasSoportados = new ArrayList();
        Iterator<Locale> it = fc.getApplication().getSupportedLocales();
        while (it.hasNext()){
            idiomasSoportados.add(it.next());
        }
        
    }

    public Languages() {
    }

    public Locale getIdiomaSeleccionado() {
        return idiomaSeleccionado;
    }

    public void setIdiomaSeleccionado(Locale idiomaSeleccionado) {
        this.idiomaSeleccionado = idiomaSeleccionado;
    }

    public List<Locale> getIdiomasSoportados() {
        return idiomasSoportados;
    }

    public void setIdiomasSoportados(List<Locale> idiomasSoportados) {
        this.idiomasSoportados = idiomasSoportados;
    }
    
    public void cambiarIdioma (Locale nuevoIdioma){
        this.idiomaSeleccionado = nuevoIdioma;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(nuevoIdioma);
    }
}
