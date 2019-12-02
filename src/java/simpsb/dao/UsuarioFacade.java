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
            Query query = em.createQuery("SELECT u FROM TblUsuarios u WHERE u.correo = :correo AND u.pass = :pass");
            query.setParameter("correo", user.getCorreo());
            query.setParameter("clave", user.getPass());
            List<Usuario> listUser = query.getResultList();
            if (!listUser.isEmpty()) {
                usuario = listUser.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    @Override
    public Usuario getId(int numDocumento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
