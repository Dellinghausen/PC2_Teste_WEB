package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Estudante;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Tiago
 */
@Stateless
public class EstudanteDAO<TIPO> extends DAOGenerico<Estudante> implements Serializable {

    public EstudanteDAO() {
        super();
        classePersistente = Estudante.class;
    }

    @Override
    public Estudante getObjectById(Object id) throws Exception {
        Estudante obj = em.find(Estudante.class, id);
        obj.getNecessidade().size();
        obj.getAcaoposterior().size();
        return obj;
    }
}
