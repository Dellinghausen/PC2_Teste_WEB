package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tiago
 * @param <TIPO>
 */
public class DAOGenerico<TIPO> implements Serializable {

    private List<TIPO> listaObjetos;
    @PersistenceContext(unitName = "PC2_Teste_WebPU2")
    protected EntityManager em;
    protected Class classePersistente;
    protected String ordem = "id";
    protected String filtro = "";
    protected Integer maximoObjetos = 10;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;

    public DAOGenerico() {
    }

    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if (getFiltro().length() > 0) {
            if (getOrdem().equals("id")) {
                try {
                    Integer.parseInt(getFiltro());
                    where += " where " + getOrdem() + " = '" + getFiltro() + "' ";
                } catch (NumberFormatException e) {
                }
            } else {
                where += " where upper(" + getOrdem() + ") like '" + getFiltro().toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).
                setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();
    }
    
        public void primeiro() {
        posicaoAtual = 0;
    }

    public void anterior() {
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual < 0) {
            posicaoAtual = 0;
        }
    }

    public void proximo() {
        if (posicaoAtual + maximoObjetos < totalObjetos) {
            posicaoAtual += maximoObjetos;
        }
    }

    public void ultimo() {
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0) {
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }

    public String getMensagemNavegacao() {
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos) {
            ate = totalObjetos;
        }
        if (totalObjetos > 0) {
            return "Listagem de " + (posicaoAtual + 1) + " até " + ate
                    + " de " + totalObjetos + " registros";
        } else {
            return "Nenhum registro encontrado!";
        }
    }

    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    public void persist(TIPO obj) throws Exception {
        em.persist(obj);
    }

    public void merge(TIPO obj) throws Exception {
        em.merge(obj);
    }

    public TIPO getObjectById(Object id) throws Exception {
        return (TIPO) em.find(classePersistente, id);
    }

    //@RolesAllowed("ADMINISTRADOR")
    public void remover(TIPO obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }
}
