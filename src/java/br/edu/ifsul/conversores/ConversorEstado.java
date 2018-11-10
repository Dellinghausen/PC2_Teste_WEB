package br.edu.ifsul.conversores;

import br.edu.ifsul.modelo.Estado;
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
//@FacesConverter(value = "conversorEstado")
@Named(value = "conversorEstado")
@RequestScoped
public class ConversorEstado implements Converter, Serializable {
    
    @PersistenceContext(unitName = "PC2_Teste_WebPU2")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("Selecione um registro")){
            return null;
        }
        
        Estado retornoO = em.find(Estado.class, Integer.parseInt(value));
        System.out.println("Objeto--->  "+retornoO);
        return retornoO;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null){
            return null;
        }        
        Estado obj = (Estado) value;
        String retornoS = obj.getId().toString();
        System.out.println("String--->  "+retornoS);
        return retornoS;
    }
    
}
