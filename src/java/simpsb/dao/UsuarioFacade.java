/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpsb.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import simpsb.entidades.Usuario;

/**
 *
 * @author SebastianParra
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "SIMPSB1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario login(Usuario user) {
        Usuario usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario WHERE u.correo = :correo ADN u.pass = :pass");
            query.setParameter("correo", user.getCorreo());
            query.setParameter("pass", user.getPass());
            List<Usuario> listUsu = query.getResultList();
            if (!listUsu.isEmpty()) {
                usuario = listUsu.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
    
}
