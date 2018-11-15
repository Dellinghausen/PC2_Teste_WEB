package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.RespostaTexto;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.Query;
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
    
    public RespostaTexto localizaPorAluno(Object dadosId, Object perguntaId) {
        Query query = em.createQuery("from RespostaTexto where dadosresposta_id = " + dadosId + " AND pergunta_id " + perguntaId);
        RespostaTexto obj = (RespostaTexto) query.getSingleResult();
        return obj;
    }
}
