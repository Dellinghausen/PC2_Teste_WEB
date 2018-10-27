package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.DadosRespostaDAO;
import br.edu.ifsul.dao.EstudanteDAO;
import br.edu.ifsul.dao.PerguntaDAO;
import br.edu.ifsul.dao.QuestionarioDAO;
import br.edu.ifsul.dao.RespostaComEscolhaDAO;
import br.edu.ifsul.dao.RespostaTextoDAO;
import br.edu.ifsul.modelo.DadosResposta;
import br.edu.ifsul.modelo.Estudante;
import br.edu.ifsul.modelo.Pergunta;
import br.edu.ifsul.modelo.Questionario;
import br.edu.ifsul.modelo.RespostaComEscolha;
import br.edu.ifsul.modelo.RespostaTexto;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tiago
 */
@Named(value = "controleResponder")
@ViewScoped
public class ControleResponder implements Serializable {

    @EJB
    private DadosRespostaDAO<DadosResposta> dao;
    private DadosResposta objeto;
    private Boolean editando;
    @EJB
    private EstudanteDAO<Estudante> daoEstudante;
    @EJB
    private QuestionarioDAO<Questionario> daoQuestionario;
    private Questionario questionario;
    @EJB
    private PerguntaDAO<Pergunta> daoPergunta;
    @EJB
    private RespostaTextoDAO<RespostaTexto> daoRespostaTexto;
    private RespostaTexto respostaTexto;
    @EJB
    private RespostaComEscolhaDAO<RespostaComEscolha> daoRespostaEscolha;
    private RespostaComEscolha respostaEscolha;
    @Inject
    private ControleLogin logado;

    public ControleResponder() {
        editando = false;
//        daoPergunta.setMaximoObjetos(1);
//        objeto = dao.localizaPorAluno(logado.getUsuarioAutenticado().getId());
    }

    public String listar() {
        return "/privado/responder/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new DadosResposta();
        editando = true;
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            br.edu.ifsul.util.Util.mensagemErro("Erro ao recuperar objeto: " + br.edu.ifsul.util.Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
        } catch (Exception e) {
            br.edu.ifsul.util.Util.mensagemErro("Erro ao remover objeto: " + br.edu.ifsul.util.Util.getMensagemErro(e));
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
            br.edu.ifsul.util.Util.mensagemErro("Erro ao persistir objeto: " + br.edu.ifsul.util.Util.getMensagemErro(e));
        }
    }

    public Integer pegaDadosPorUser() {
        objeto = dao.localizaPorAluno(logado.getUsuarioAutenticado().getId());
        return objeto.getId();
    }
    
    public Questionario pegaQuestionarioPorUser() {
        objeto = dao.localizaPorAluno(logado.getUsuarioAutenticado().getId());
        questionario = objeto.getQuestionario();
        return questionario;
    }


    public DadosRespostaDAO<DadosResposta> getDao() {
        return dao;
    }

    public void setDao(DadosRespostaDAO<DadosResposta> dao) {
        this.dao = dao;
    }

    public DadosResposta getObjeto() {
        return objeto;
    }

    public void setObjeto(DadosResposta objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public EstudanteDAO<Estudante> getDaoEstudante() {
        return daoEstudante;
    }

    public void setDaoEstudante(EstudanteDAO<Estudante> daoEstudante) {
        this.daoEstudante = daoEstudante;
    }

    public QuestionarioDAO<Questionario> getDaoQuestionario() {
        return daoQuestionario;
    }

    public void setDaoQuestionario(QuestionarioDAO<Questionario> daoQuestionario) {
        this.daoQuestionario = daoQuestionario;
    }

    public RespostaTextoDAO<RespostaTexto> getDaoRespostaTexto() {
        return daoRespostaTexto;
    }

    public void setDaoRespostaTexto(RespostaTextoDAO<RespostaTexto> daoRespostaTexto) {
        this.daoRespostaTexto = daoRespostaTexto;
    }

    public RespostaTexto getRespostaTexto() {
        return respostaTexto;
    }

    public void setRespostaTexto(RespostaTexto respostaTexto) {
        this.respostaTexto = respostaTexto;
    }

    public RespostaComEscolhaDAO<RespostaComEscolha> getDaoRespostaEscolha() {
        return daoRespostaEscolha;
    }

    public void setDaoRespostaEscolha(RespostaComEscolhaDAO<RespostaComEscolha> daoRespostaEscolha) {
        this.daoRespostaEscolha = daoRespostaEscolha;
    }

    public RespostaComEscolha getRespostaEscolha() {
        return respostaEscolha;
    }

    public void setRespostaEscolha(RespostaComEscolha respostaEscolha) {
        this.respostaEscolha = respostaEscolha;
    }

    public ControleLogin getLogado() {
        return logado;
    }

    public void setLogado(ControleLogin logado) {
        this.logado = logado;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public PerguntaDAO<Pergunta> getDaoPergunta() {
        return daoPergunta;
    }

    public void setDaoPergunta(PerguntaDAO<Pergunta> daoPergunta) {
        this.daoPergunta = daoPergunta;
    }

}
