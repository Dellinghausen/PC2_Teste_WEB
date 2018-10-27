package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Questionario;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class QuestionarioDAO<TIPO> extends DAOGenerico<Questionario> implements Serializable {

    public QuestionarioDAO() {
        super();
        classePersistente = Questionario.class;
    }   
}
