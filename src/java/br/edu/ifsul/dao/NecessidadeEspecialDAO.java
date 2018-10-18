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
}
