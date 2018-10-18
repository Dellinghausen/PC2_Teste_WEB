package br.edu.ifsul.conversores;

import br.edu.ifsul.modelo.Estudante;
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
@FacesConverter(value = "conversorEstudante")
public class ConversorEstudante implements Converter, Serializable {
    
    @PersistenceContext(unitName = "PC2_Teste_WebPU2")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("Selecione um registro")){
            return null;
        }
        return em.find(Estudante.class, Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null){
            return null;
        }
        Estudante obj = (Estudante) value;
        return obj.getId().toString();
    }
    
}
