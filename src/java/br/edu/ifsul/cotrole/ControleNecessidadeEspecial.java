package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.NecessidadeEspecialDAO;
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
@Named(value = "controleNecessidadeEspecial")
@ViewScoped
public class ControleNecessidadeEspecial implements Serializable{
    @EJB
    private NecessidadeEspecialDAO<NecessidadeEspecial> dao;
    private NecessidadeEspecial objeto;
    private Boolean editando;

    public ControleNecessidadeEspecial() {
        editando = false;
    }

    public String listar() {
        return "/privado/necessidade/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new NecessidadeEspecial();
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

    public NecessidadeEspecialDAO<NecessidadeEspecial> getDao() {
        return dao;
    }

    public void setDao(NecessidadeEspecialDAO<NecessidadeEspecial> dao) {
        this.dao = dao;
    }

    public NecessidadeEspecial getObjeto() {
        return objeto;
    }

    public void setObjeto(NecessidadeEspecial objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }
    
    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
