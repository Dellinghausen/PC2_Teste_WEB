package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.CategoriaDAO;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Tiago
 */
@Named(value = "controleCategoria")
@ViewScoped
public class ControleCategoria implements Serializable{
    @EJB
    private CategoriaDAO<Categoria> dao;
    private Categoria objeto;
    private Boolean editando;

    public ControleCategoria() {
        editando = false;
    }

    public String listar() {
        return "/privado/categoria/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Categoria();
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

    public CategoriaDAO<Categoria> getDao() {
        return dao;
    }

    public void setDao(CategoriaDAO<Categoria> dao) {
        this.dao = dao;
    }

    public Categoria getObjeto() {
        return objeto;
    }

    public void setObjeto(Categoria objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }
    
    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
