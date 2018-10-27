package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.DadosResposta;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Tiago
 */
@Stateless
public class DadosRespostaDAO<TIPO> extends DAOGenerico<DadosResposta> implements Serializable {

    public DadosRespostaDAO() {
        super();
        classePersistente = DadosResposta.class;
    }

    @Override
    public DadosResposta getObjectById(Object id) throws Exception {
        DadosResposta obj = em.find(DadosResposta.class, id);
        obj.getRespostaTexto().size();
        obj.getRespostaEscolha().size();
        return obj;
    }

    public DadosResposta localizaPorAluno(Object usuarioId) {
        Query query = em.createQuery("from DadosResposta where estudante_id = "+usuarioId);
        DadosResposta obj = (DadosResposta) query.getSingleResult();
        return obj;
    }
}
