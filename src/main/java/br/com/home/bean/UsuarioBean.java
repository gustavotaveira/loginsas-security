package br.com.home.bean;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;

import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;


@SessionScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean implements Serializable {

    private Application app = FacesContext.getCurrentInstance().getApplication();
    private String nome;
    private String senha;
    private HtmlForm mainForm;

    public UsuarioBean() {
        mainForm = (HtmlForm) app.createComponent(HtmlForm.COMPONENT_TYPE);
        mainForm.setId("mainform");

        PanelGrid layout = (PanelGrid) app.createComponent(PanelGrid.COMPONENT_TYPE);
        layout.setColumns(3);
        layout.setLayout("grid");
        layout.setStyle("width: 100%; border: none !important; height: 100%");
        OutputLabel emptyLabel = (OutputLabel) app.createComponent(OutputLabel.COMPONENT_TYPE);
        layout.getChildren().add(emptyLabel);

        PanelGrid panelGrid = (PanelGrid) app.createComponent(PanelGrid.COMPONENT_TYPE);
        panelGrid.setColumns(1);
        panelGrid.setLayout("grid");
        panelGrid.setStyle("width: 100%%; height:300px");

        OutputLabel nomeLabel = (OutputLabel) app.createComponent(OutputLabel.COMPONENT_TYPE);
        nomeLabel.setValue("Nome: ");
        nomeLabel.setFor("nome");
        nomeLabel.setStyle("width: 100%;");
        panelGrid.getChildren().add(nomeLabel);

        InputText nomeInput = (InputText) app.createComponent(InputText.COMPONENT_TYPE);
        nomeInput.setId("nome");
        nomeInput.setStyle("width: 100%");
        nomeInput.setValueExpression("value", app.getExpressionFactory()
                .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{usuarioBean.nome}", String.class));
        panelGrid.getChildren().add(nomeInput);

        OutputLabel senhaLabel = (OutputLabel) app.createComponent(OutputLabel.COMPONENT_TYPE);
        senhaLabel.setValue("Senha: ");
        senhaLabel.setFor("senha");
        senhaLabel.setStyle("width: 100%;");
        panelGrid.getChildren().add(senhaLabel);

        InputText senhaInput = (InputText) app.createComponent(InputText.COMPONENT_TYPE);
        senhaInput.setId("senha");
        senhaInput.setStyle("width: 100%");
        senhaInput.setValueExpression("value", app.getExpressionFactory()
                .createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{usuarioBean.senha}", String.class));
        panelGrid.getChildren().add(senhaInput);

        CommandButton buttonEntrar = (CommandButton) app.createComponent(CommandButton.COMPONENT_TYPE);
        MethodExpression me = app.getExpressionFactory().createMethodExpression(FacesContext.getCurrentInstance().getELContext(),
                "#{usuarioBean.processForm}", void.class, new Class[0]);
        buttonEntrar.setActionExpression(me);
        buttonEntrar.setValue("Enviar");
        buttonEntrar.setStyle("width: 100%;");
        panelGrid.getChildren().add(buttonEntrar);

        layout.getChildren().add(panelGrid);

        mainForm.getChildren().add(layout);
    }


    public void processForm() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/loginsas/faces/success");
        } catch (IOException e) {
            throw new RuntimeException("caminho nao encontrado");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public HtmlForm getMainForm() {
        return mainForm;
    }

    public void setMainForm(HtmlForm mainForm) {
        this.mainForm = mainForm;
    }
}
