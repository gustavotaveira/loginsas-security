package br.com.home.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class JSFUtils {

    public static UIComponent findComponentById(String id) {
        return FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
    }
}
