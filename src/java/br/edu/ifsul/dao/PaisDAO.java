package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pais;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class PaisDAO<TIPO> extends DAOGenerico<Pais> implements Serializable {

    public PaisDAO() {
        super();
        classePersistente = Pais.class;
    }
}
