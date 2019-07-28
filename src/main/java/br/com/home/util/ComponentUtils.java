package br.com.home.util;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

public class ComponentUtils {

    public static HtmlOutputText createHtmlOutputText() {
        return (HtmlOutputText) FacesContext.getCurrentInstance().getApplication()
                .createComponent(HtmlOutputText.COMPONENT_TYPE);
    }
}
