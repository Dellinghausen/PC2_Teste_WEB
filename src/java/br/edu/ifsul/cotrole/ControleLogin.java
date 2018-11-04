package br.edu.ifsul.cotrole;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Tiago
 */

@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    private Usuario usuarioAutenticado;
    private String permissaoLogado;
    @EJB
    private UsuarioDAO<Usuario> dao;
    private String login;
    private String senha;

    public ControleLogin() {
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try {
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.login, this.senha);
            if (request.getUserPrincipal() != null){
                usuarioAutenticado = dao.localizaPorNomeUsuario(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login efetuado com sucesso!");
                login= "";
                senha = "";
            }
            return "/index";            
        } catch (Exception e){
            e.printStackTrace();
            Util.mensagemErro("Usuário ou senha inválidos! " + 
                    Util.getMensagemErro(e));
            return "/login";
        }
    }
    
    public String efetuarLogout(){
        try {
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();            
        } catch (Exception e){
            Util.mensagemErro("Erro: " + Util.getMensagemErro(e));            
        }
        return "/index";
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissaoLogado() {
        return permissaoLogado;
    }

    public void setPermissaoLogado(String permissaoLogado) {
        this.permissaoLogado = permissaoLogado;
    }

}
