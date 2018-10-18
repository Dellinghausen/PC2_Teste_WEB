package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.PerguntaDAO;
import br.edu.ifsul.dao.CategoriaDAO;
import br.edu.ifsul.dao.QuestionarioDAO;
import br.edu.ifsul.modelo.Pergunta;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.OpcaoResposta;
import br.edu.ifsul.modelo.Questionario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Tiago
 */
@Named(value = "controlePergunta")
@ViewScoped
public class ControlePergunta implements Serializable {

    @EJB
    private PerguntaDAO<Pergunta> dao;
    private Pergunta objeto;
    private Boolean editando;
    @EJB
    private CategoriaDAO<Categoria> daoCategoria;
    @EJB
    private QuestionarioDAO<Questionario> daoQuestionario;
    private OpcaoResposta opcaoResposta;
    private Boolean novaOpcaoResposta;

    public ControlePergunta() {
        editando = false;
        dao = new PerguntaDAO<>();
    }

    public String listar() {
        return "/privado/pergunta/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Pergunta();
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

    public void novoOpcaoResposta() {
        opcaoResposta = new OpcaoResposta();
        novaOpcaoResposta = true;
    }

    public void alterarOpcaoResposta(int index) {
        opcaoResposta = objeto.getPossibilidadeResposta().get(index);
        novaOpcaoResposta = false;
    }

    public void salvarOpcaoResposta() {
        if (novaOpcaoResposta) {
            objeto.adicionarOpcaoResposta(opcaoResposta);
        }
        Util.mensagemInformacao("OpcaoResposta persistido com sucesso!");
    }

    public void removerOpcaoResposta(int index) {
        objeto.removerOpcaoResposta(index);
        Util.mensagemInformacao("OpcaoResposta removido com sucesso!");
    }

    public PerguntaDAO<Pergunta> getDao() {
        return dao;
    }

    public void setDao(PerguntaDAO<Pergunta> dao) {
        this.dao = dao;
    }

    public Pergunta getObjeto() {
        return objeto;
    }

    public void setObjeto(Pergunta objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public CategoriaDAO<Categoria> getDaoCategoria() {
        return daoCategoria;
    }

    public void setDaoCategoria(CategoriaDAO<Categoria> daoCategoria) {
        this.daoCategoria = daoCategoria;
    }

    public QuestionarioDAO<Questionario> getDaoQuestionario() {
        return daoQuestionario;
    }

    public void setDaoQuestionario(QuestionarioDAO<Questionario> daoQuestionario) {
        this.daoQuestionario = daoQuestionario;
    }

    public OpcaoResposta getOpcaoResposta() {
        return opcaoResposta;
    }

    public void setOpcaoResposta(OpcaoResposta opcaoResposta) {
        this.opcaoResposta = opcaoResposta;
    }

    public Boolean getNovaOpcaoResposta() {
        return novaOpcaoResposta;
    }

    public void setNovaOpcaoResposta(Boolean novaOpcaoResposta) {
        this.novaOpcaoResposta = novaOpcaoResposta;
    }
}
