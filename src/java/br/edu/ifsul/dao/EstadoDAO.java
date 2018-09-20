package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class EstadoDAO<TIPO> extends DAOGenerico<Estado> implements Serializable {

    public EstadoDAO() {
        super();
        classePersistente = Estado.class;
    }
}
