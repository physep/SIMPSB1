/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import simpsb.entidades.FotosPerfil;

/**
 *
 * @author LeonardoLara
 */
@Stateless
public class FotosPerfilFacade extends AbstractFacade<FotosPerfil> implements FotosPerfilFacadeLocal {

    @PersistenceContext(unitName = "SIMPSB1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FotosPerfilFacade() {
        super(FotosPerfil.class);
    }
    
}
