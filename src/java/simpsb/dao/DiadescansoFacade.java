/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import simpsb.entidades.Diadescanso;

/**
 *
 * @author LeonardoLara
 */
@Stateless
public class DiadescansoFacade extends AbstractFacade<Diadescanso> implements DiadescansoFacadeLocal {

    @PersistenceContext(unitName = "SIMPSB1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiadescansoFacade() {
        super(Diadescanso.class);
    }
    
}
