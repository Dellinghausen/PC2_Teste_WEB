package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.OpcaoRespostaDAO;
import br.edu.ifsul.dao.PerguntaDAO;
import br.edu.ifsul.modelo.OpcaoResposta;
import br.edu.ifsul.modelo.Pergunta;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Tiago
 */
@Named(value = "controleOpcaoResposta")
@ViewScoped
public class ControleOpcaoResposta implements Serializable {

    @EJB
    private OpcaoRespostaDAO<OpcaoResposta> dao;
    private OpcaoResposta objeto;
    private Boolean editando;
    @EJB
    private PerguntaDAO<Pergunta> daoPergunta;

    public ControleOpcaoResposta() {
        editando = false;
        daoPergunta = new PerguntaDAO<>();
    }

    public String listar() {
        return "/privado/opcaoresposta/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new OpcaoResposta();
        editando = true;
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public OpcaoRespostaDAO<OpcaoResposta> getDao() {
        return dao;
    }

    public void setDao(OpcaoRespostaDAO<OpcaoResposta> dao) {
        this.dao = dao;
    }

    public OpcaoResposta getObjeto() {
        return objeto;
    }

    public void setObjeto(OpcaoResposta objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public PerguntaDAO<Pergunta> getDaoPergunta() {
        return daoPergunta;
    }

    public void setDaoPergunta(PerguntaDAO<Pergunta> daoPergunta) {
        this.daoPergunta = daoPergunta;
    }
}
