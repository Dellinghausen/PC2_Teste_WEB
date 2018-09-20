package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cidade;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class CidadeDAO<TIPO> extends DAOGenerico<Cidade> implements Serializable {

    public CidadeDAO() {
        super();
        classePersistente = Cidade.class;
    }
}
