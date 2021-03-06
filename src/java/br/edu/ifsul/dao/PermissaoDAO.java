package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Permissao;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Tiago
 */
@Stateful
public class PermissaoDAO<TIPO> extends DAOGenerico<Permissao> implements Serializable{

    public PermissaoDAO() {
        super();
        classePersistente=Permissao.class;
        ordem="tipo";
        maximoObjetos = 3;
    }
}
