package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.RespostaComEscolha;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class RespostaComEscolhaDAO<TIPO> extends DAOGenerico<RespostaComEscolha> implements Serializable {

    public RespostaComEscolhaDAO() {
        super();
        classePersistente = RespostaComEscolha.class;
    }
}
