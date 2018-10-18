package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.AcaoPosteriorDAO;
import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.EstudanteDAO;
import br.edu.ifsul.dao.NecessidadeEspecialDAO;
import br.edu.ifsul.modelo.AcaoPosterior;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estudante;
import br.edu.ifsul.modelo.NecessidadeEspecial;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Tiago
 */
@Named(value = "controleEstudante")
@ViewScoped
public class ControleEstudante implements Serializable {

    @EJB
    private EstudanteDAO<Estudante> dao;
    private Estudante objeto;
    private Boolean editando;
    @EJB
    private CidadeDAO<Cidade> daoCidade;
    @EJB
    private AcaoPosteriorDAO<AcaoPosterior> daoAcaoPosterior;
    @EJB
    private NecessidadeEspecialDAO<NecessidadeEspecial> daoNecessidadeEspecial;
    private AcaoPosterior acao;
    private NecessidadeEspecial necessidade;
    private Boolean novaAcao;
    

    public ControleEstudante() {
        editando = false;
    }

    public String listar() {
        return "/privado/estudante/listar?faces-redirect=true";
    }

    public void adicionarNecessidade() {
        if (necessidade != null) {
            if (!objeto.getNecessidade().contains(necessidade)) {
                objeto.getNecessidade().add(necessidade);
                Util.mensagemInformacao("Necessidade especial adicionado com sucesso!");
            } else {
                Util.mensagemErro("Esta necessidade especial já existe na sua lista!");
            }
        }
    }

    public void novoAcao() {
        acao = new AcaoPosterior();
        novaAcao = true;
    }

    public void alterarAcao(int index) {
        acao = objeto.getAcaoposterior().get(index);
        novaAcao = false;
    }

    public void salvarAcao() {
        if (novaAcao) {
            objeto.adicionarAcao(acao);
        }
        Util.mensagemInformacao("Acao posterior persistida com sucesso!");
    }

    public void removerAcao(int index) {
        objeto.removerAcao(index);
        Util.mensagemInformacao("Acao posterior removida com sucesso!");
    }

    public void novo() {
        objeto = new Estudante();
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

    public void removerNecessidade(int index) {
        necessidade = objeto.getNecessidade().get(index);
        objeto.getNecessidade().remove(necessidade);
        Util.mensagemInformacao("Necessidade especial removida com sucesso!");
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

    public EstudanteDAO<Estudante> getDao() {
        return dao;
    }

    public void setDao(EstudanteDAO<Estudante> dao) {
        this.dao = dao;
    }

    public Estudante getObjeto() {
        return objeto;
    }

    public void setObjeto(Estudante objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public AcaoPosteriorDAO<AcaoPosterior> getDaoAcaoPosterior() {
        return daoAcaoPosterior;
    }

    public void setDaoAcaoPosterior(AcaoPosteriorDAO<AcaoPosterior> daoAcaoPosterior) {
        this.daoAcaoPosterior = daoAcaoPosterior;
    }

    public NecessidadeEspecialDAO<NecessidadeEspecial> getDaoNecessidadeEspecial() {
        return daoNecessidadeEspecial;
    }

    public void setDaoNecessidadeEspecial(NecessidadeEspecialDAO<NecessidadeEspecial> daoNecessidadeEspecial) {
        this.daoNecessidadeEspecial = daoNecessidadeEspecial;
    }

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    public AcaoPosterior getAcao() {
        return acao;
    }

    public void setAcao(AcaoPosterior acao) {
        this.acao = acao;
    }

    public NecessidadeEspecial getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(NecessidadeEspecial necessidade) {
        this.necessidade = necessidade;
    }

    public Boolean getNovaAcao() {
        return novaAcao;
    }

    public void setNovaAcao(Boolean novaAcao) {
        this.novaAcao = novaAcao;
    }
}
