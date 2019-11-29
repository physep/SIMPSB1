/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import java.util.List;
import javax.ejb.Local;
import simpsb.entidades.Diadescanso;

/**
 *
 * @author LeonardoLara
 */
@Local
public interface DiadescansoFacadeLocal {

    void create(Diadescanso diadescanso);

    void edit(Diadescanso diadescanso);

    void remove(Diadescanso diadescanso);

    Diadescanso find(Object id);

    List<Diadescanso> findAll();

    List<Diadescanso> findRange(int[] range);

    int count();
    
}
