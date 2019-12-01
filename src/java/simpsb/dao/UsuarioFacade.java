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

    public Usuario login(Usuario user) {
        Usuario usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.pass = :pass");
            query.setParameter("correo", user.getCorreo());
            query.setParameter("pass", user.getPass());
            List<Usuario> listUser = query.getResultList();
            if (!listUser.isEmpty()) {
                usuario = listUser.get(0);
            }

        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public Usuario getId(int doc) {
        Usuario user = new Usuario();
        Usuario usuario = null;
        try {
            Query query = em.createQuery("SELECT u.idUsuario FROM Usuario u WHERE u.numDocumento = :doc");
            query.setParameter("doc", doc);
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(user.getIdUsuario());
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

}
