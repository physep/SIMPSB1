/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import java.util.List;
import javax.ejb.Local;
import simpsb.entidades.Fotosperfil;

/**
 *
 * @author SebastianParra
 */
@Local
public interface FotosperfilFacadeLocal {

    void create(Fotosperfil fotosperfil);

    void edit(Fotosperfil fotosperfil);

    void remove(Fotosperfil fotosperfil);

    Fotosperfil find(Object id);

    List<Fotosperfil> findAll();

    List<Fotosperfil> findRange(int[] range);

    int count();
    
}
