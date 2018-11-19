package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.PerguntaDAO;
import br.edu.ifsul.dao.QuestionarioDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Pergunta;
import br.edu.ifsul.modelo.Questionario;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tiago
 */
@Named(value = "controleQuestionario")
@ViewScoped
public class ControleQuestionario implements Serializable {

    @EJB
    private QuestionarioDAO<Questionario> dao;
    private Questionario objeto;
    private Boolean editando;
    @EJB
    private UsuarioDAO<Usuario> daoUsuario;
    @Inject
    private ControleLogin logado;
    @EJB
    private PerguntaDAO<Pergunta> daoPergunta;
    private Pergunta pergunta;

    public ControleQuestionario() {
        editando = false;
    }

    public String listar() {
        return "/privado/questionario/listar?faces-redirect=true";
    }

    public void adicionarPergunta() {
        if (pergunta != null) {
            if (!objeto.getPergunta().contains(pergunta)) {
                objeto.getPergunta().add(pergunta);
                Util.mensagemInformacao("Pergunta adicionado com sucesso!");
            } else {
                Util.mensagemErro("Esta pergunta já existe na sua lista!");
            }
        }
    }

    public void removerPergunta(int index) {
        pergunta = objeto.getPergunta().get(index);
        objeto.getPergunta().remove(pergunta);
        Util.mensagemInformacao("Pergunta removida com sucesso!");
    }

    public void novo() {
        objeto = new Questionario();
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
                objeto.setUsuario(logado.getUsuarioAutenticado());
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public QuestionarioDAO<Questionario> getDao() {
        return dao;
    }

    public void setDao(QuestionarioDAO<Questionario> dao) {
        this.dao = dao;
    }

    public Questionario getObjeto() {
        return objeto;
    }

    public void setObjeto(Questionario objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public ControleLogin getLogado() {
        return logado;
    }

    public void setLogado(ControleLogin logado) {
        this.logado = logado;
    }

    public PerguntaDAO<Pergunta> getDaoPergunta() {
        return daoPergunta;
    }

    public void setDaoPergunta(PerguntaDAO<Pergunta> daoPergunta) {
        this.daoPergunta = daoPergunta;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }
}
