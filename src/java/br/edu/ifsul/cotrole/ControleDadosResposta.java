package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.DadosRespostaDAO;
import br.edu.ifsul.dao.EstudanteDAO;
import br.edu.ifsul.dao.QuestionarioDAO;
import br.edu.ifsul.dao.RespostaComEscolhaDAO;
import br.edu.ifsul.dao.RespostaTextoDAO;
import br.edu.ifsul.modelo.DadosResposta;
import br.edu.ifsul.modelo.Estudante;
import br.edu.ifsul.modelo.Questionario;
import br.edu.ifsul.modelo.RespostaComEscolha;
import br.edu.ifsul.modelo.RespostaTexto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Tiago
 */
@Named(value = "controleDadosResposta")
@ViewScoped
public class ControleDadosResposta implements Serializable {

    @EJB
    private DadosRespostaDAO<DadosResposta> dao;
    private DadosResposta objeto;
    private Boolean editando;
    @EJB
    private EstudanteDAO<Estudante> daoEstudante;
    private Estudante estudante;
    @EJB
    private QuestionarioDAO<Questionario> daoQuestionario;
    private Questionario questionario;
    @EJB
    private RespostaTextoDAO<RespostaTexto> daoRespostaTexto;
    private RespostaTexto respostaTexto;
    @EJB
    private RespostaComEscolhaDAO<RespostaComEscolha> daoRespostaEscolha;
    private RespostaComEscolha respostaEscolha;

    public ControleDadosResposta() {
        editando = false;
    }

    public String listar() {
        return "/privado/dadosresposta/listar?faces-redirect=true";
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

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }
}
