/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import java.util.List;
import javax.ejb.Local;
import simpsb.entidades.Horariotrabajo;

/**
 *
 * @author SebastianParra
 */
@Local
public interface HorariotrabajoFacadeLocal {

    void create(Horariotrabajo horariotrabajo);

    void edit(Horariotrabajo horariotrabajo);

    void remove(Horariotrabajo horariotrabajo);

    Horariotrabajo find(Object id);

    List<Horariotrabajo> findAll();

    List<Horariotrabajo> findRange(int[] range);

    int count();
    
}
