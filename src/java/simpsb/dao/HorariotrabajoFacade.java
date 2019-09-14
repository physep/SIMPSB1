/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import simpsb.entidades.Horariotrabajo;

/**
 *
 * @author APRENDIZ
 */
@Stateless
public class HorariotrabajoFacade extends AbstractFacade<Horariotrabajo> implements HorariotrabajoFacadeLocal {

    @PersistenceContext(unitName = "SIMPSB1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorariotrabajoFacade() {
        super(Horariotrabajo.class);
    }
    
}
