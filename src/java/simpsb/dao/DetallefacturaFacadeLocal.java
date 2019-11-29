/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import java.util.List;
import javax.ejb.Local;
import simpsb.entidades.Detallefactura;

/**
 *
 * @author LeonardoLara
 */
@Local
public interface DetallefacturaFacadeLocal {

    void create(Detallefactura detallefactura);

    void edit(Detallefactura detallefactura);

    void remove(Detallefactura detallefactura);

    Detallefactura find(Object id);

    List<Detallefactura> findAll();

    List<Detallefactura> findRange(int[] range);

    int count();
    
}
