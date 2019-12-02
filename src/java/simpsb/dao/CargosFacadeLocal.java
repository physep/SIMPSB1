/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import java.util.List;
import javax.ejb.Local;
import simpsb.entidades.Cargos;

/**
 *
 * @author Leonardo Lara
 */
@Local
public interface CargosFacadeLocal {

    void create(Cargos cargos);

    void edit(Cargos cargos);

    void remove(Cargos cargos);

    Cargos find(Object id);

    List<Cargos> findAll();

    List<Cargos> findRange(int[] range);

    int count();
    
}
