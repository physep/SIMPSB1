package simpsb.controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Utils implements Serializable {
    
    public void add(String key, Object value){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    
    public void get(String key){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }
}
