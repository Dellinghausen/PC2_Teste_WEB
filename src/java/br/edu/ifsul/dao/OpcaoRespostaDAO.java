package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.OpcaoResposta;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Tiago
 */
@Stateless
public class OpcaoRespostaDAO<TIPO> extends DAOGenerico<OpcaoResposta> implements Serializable {

    public OpcaoRespostaDAO() {
        super();
        classePersistente = OpcaoResposta.class;
    }

    @Override
    public OpcaoResposta getObjectById(Object id) throws Exception {
        OpcaoResposta obj = em.find(OpcaoResposta.class, id);
        obj.getRespostaEscolha().size();
        return obj;
    }
}
