package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.RespostaTexto;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class RespostaTextoDAO<TIPO> extends DAOGenerico<RespostaTexto> implements Serializable {

    public RespostaTextoDAO() {
        super();
        classePersistente = RespostaTexto.class;
    }
}
