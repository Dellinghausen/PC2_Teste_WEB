package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.DadosResposta;
import java.io.Serializable;
import javax.ejb.Stateless;
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
}
