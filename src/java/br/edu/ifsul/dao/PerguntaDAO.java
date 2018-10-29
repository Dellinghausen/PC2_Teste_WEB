package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pergunta;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class PerguntaDAO<TIPO> extends DAOGenerico<Pergunta> implements Serializable {

    public PerguntaDAO() {
        super();
        classePersistente = Pergunta.class;
    }
    
    @Override
    public Pergunta getObjectById(Object id) throws Exception {
        Pergunta obj = em.find(Pergunta.class, id);
        obj.getOpcaoResposta().size();
        return obj;
    }
}
