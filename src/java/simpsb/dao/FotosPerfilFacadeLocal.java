/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import java.util.List;
import javax.ejb.Local;
import simpsb.entidades.FotosPerfil;

/**
 *
 * @author LeonardoLara
 */
@Local
public interface FotosPerfilFacadeLocal {

    void create(FotosPerfil fotosPerfil);

    void edit(FotosPerfil fotosPerfil);

    void remove(FotosPerfil fotosPerfil);

    FotosPerfil find(Object id);

    List<FotosPerfil> findAll();

    List<FotosPerfil> findRange(int[] range);

    int count();
    
}
