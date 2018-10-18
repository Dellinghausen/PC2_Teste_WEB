package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.AcaoPosterior;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class AcaoPosteriorDAO<TIPO> extends DAOGenerico<AcaoPosterior> implements Serializable {

    public AcaoPosteriorDAO() {
        super();
        classePersistente = AcaoPosterior.class;
    }
}
