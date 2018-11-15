package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.DadosResposta;
import br.edu.ifsul.modelo.OpcaoResposta;
import br.edu.ifsul.modelo.Pergunta;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.Query;

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
        for (OpcaoResposta o : obj.getOpcaoResposta()) {
            o.getRespostaEscolha().size();
        }
        return obj;
    }
}
