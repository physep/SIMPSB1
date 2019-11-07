/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import simpsb.entidades.Fotosperfil;

/**
 *
 * @author SebastianParra
 */
@Stateless
public class FotosperfilFacade extends AbstractFacade<Fotosperfil> implements FotosperfilFacadeLocal {

    @PersistenceContext(unitName = "SIMPSB1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FotosperfilFacade() {
        super(Fotosperfil.class);
    }
    
}
