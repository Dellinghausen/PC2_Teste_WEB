package br.edu.ifsul.conversores;

import br.edu.ifsul.modelo.NecessidadeEspecial;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tiago
 */
//@FacesConverter(value = "conversorNecessidade")
@Named(value = "conversorNecessidade")
@RequestScoped
public class ConversorNecessidade implements Converter, Serializable {
    
    @PersistenceContext(unitName = "PC2_Teste_WebPU2")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("Selecione um registro")){
            return null;
        }
        return em.find(NecessidadeEspecial.class, Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null){
            return null;
        }
        NecessidadeEspecial obj = (NecessidadeEspecial) value;
        return obj.getId().toString();
    }
    
}
