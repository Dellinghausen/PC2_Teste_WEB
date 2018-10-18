package br.edu.ifsul.conversores;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tiago
 */
@FacesConverter(value = "conversorUsuario")
public class ConversorUsuario implements Converter, Serializable {
    
    @PersistenceContext(unitName = "PC2_Teste_WebPU2")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("Selecione um registro")){
            return null;
        }
        return em.find(Usuario.class, Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null){
            return null;
        }
        Usuario obj = (Usuario) value;
        return obj.getId().toString();
    }
    
}
