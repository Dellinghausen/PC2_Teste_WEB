package br.edu.ifsul.conversores;

import br.edu.ifsul.modelo.Categoria;
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
//@FacesConverter(value = "conversorCategoria")
@Named(value = "conversorCategoria")
@RequestScoped
public class ConversorCategoria implements Converter, Serializable {
    
    @PersistenceContext(unitName = "PC2_Teste_WebPU2")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("Selecione um registro")){
            return null;
        }
        return em.find(Categoria.class, Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null){
            return null;
        }
        Categoria obj = (Categoria) value;
        return obj.getId().toString();
    }
    
}
