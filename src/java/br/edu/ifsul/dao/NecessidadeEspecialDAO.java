package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.NecessidadeEspecial;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Tiago
 */
@Stateless
public class NecessidadeEspecialDAO<TIPO> extends DAOGenerico<NecessidadeEspecial> implements Serializable {

    public NecessidadeEspecialDAO() {
        super();
        classePersistente = NecessidadeEspecial.class;
    }

    @Override
    public NecessidadeEspecial getObjectById(Object id) throws Exception {
        NecessidadeEspecial obj = em.find(NecessidadeEspecial.class, id);
        obj.getNecessitam().size();
        return obj;
    }
}
