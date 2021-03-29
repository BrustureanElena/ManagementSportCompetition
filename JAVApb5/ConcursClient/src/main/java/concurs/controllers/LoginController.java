package concurs.controllers;

import concurs.domain.AngajatOficiu;
import concurs.service.IConcursService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements  Controller {
        //ii bun asta aici??
        private Parent prt;
    ControllerPrincipal ctrlPrincipal;
    @FXML
     TextField idTextFieldUsername;
    @FXML
    PasswordField idTextFieldParola;

    @FXML
    Label idLabelParola;
    @FXML
    Label idLabelUsername;

    @FXML
    Button idButtonLogin;

    IConcursService service;
    Stage loginStage;
    @Override
    public void initialize() {

    }

    @Override
    public void setStage(Stage loginStage) {
        this.loginStage=loginStage;
    }


    public void handleLogin(ActionEvent actionEvent) {
        String username=idTextFieldUsername.getText();
        String password=idTextFieldParola.getText();
        AngajatOficiu angajatOficiu=new AngajatOficiu(username,password);
        try{

            service.login(angajatOficiu, ctrlPrincipal);
            //asta
            //AngajatOficiu angajatOficiu=new AngajatOficiu(username,password);
            Stage stage=new Stage();
            stage.setScene(new Scene(prt));

            stage.setOnCloseRequest(event -> {
                ctrlPrincipal.logout();
                System.exit(0);
            });
            //asta
            ctrlPrincipal.setAngajatOficiuConnectat(angajatOficiu);


            stage.show();

            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }catch(Exception e){
            MessageBox.showErrorMessage(null,e.getMessage());
        }

    }
    public void setContext(IConcursService service){
        this.service=service;

    }
    public void setControllerPrincipal(ControllerPrincipal crt){
        ctrlPrincipal=crt;
    }
    public void setParent(Parent p) {
        this.prt = p;
    }


    }
