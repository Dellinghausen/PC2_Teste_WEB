package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Categoria;
import java.io.Serializable;
import javax.ejb.Stateless;
/**
 *
 * @author Tiago
 */
@Stateless
public class CategoriaDAO<TIPO> extends DAOGenerico<Categoria> implements Serializable {

    public CategoriaDAO() {
        super();
        classePersistente = Categoria.class;
    }
}
