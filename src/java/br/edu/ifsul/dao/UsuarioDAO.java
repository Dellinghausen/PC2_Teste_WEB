package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.Query;
/**
 *
 * @author Tiago
 */
@Stateless
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable {

    public UsuarioDAO() {
        super();
        classePersistente = Usuario.class;
    }
    
    @Override
    public Usuario getObjectById(Object id) throws Exception {
        Usuario obj = em.find(Usuario.class, id);
        obj.getPermissao().size();
        return obj;
    }
    
    public Usuario localizaPorNomeUsuario(String nomeUsuario){
        Query query = em.createQuery("from Usuario where upper(login) = :nomeUsuario");
        query.setParameter("nomeUsuario",nomeUsuario.toUpperCase());
        Usuario obj = (Usuario) query.getSingleResult();
        return obj;
    }
}
