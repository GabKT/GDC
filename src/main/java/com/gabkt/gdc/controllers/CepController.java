package com.gabkt.gdc.controllers;

import com.gabkt.gdc.model.Cep;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.web.client.RestTemplate;

public class CepController{
    @FXML
    private TextField inputCep;
    @FXML
    public Button submitCep;
    @FXML
    private TableView<Cep> tableViewCep;
    @FXML
    private TableColumn<Cep, String> columnRua;
    @FXML
    private TableColumn<Cep, String> columnComplemento;
    @FXML
    private TableColumn<Cep, String> columnBairro;
    @FXML
    private TableColumn<Cep, String> columnCidadeES;
    @FXML
    private TableColumn<Cep, String> columnCep;

    @FXML
    public void submitCepAction(){
            Integer cepNumber = Integer.parseInt(inputCep.getText());
            String url = String.format("https://viacep.com.br/ws/%d/json/", cepNumber);
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            Cep cep = restTemplate.getForObject(url, Cep.class);
            System.out.println(cep);
            if(cep != null){
                columnRua.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
                columnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
                columnCidadeES.setCellValueFactory(cellData -> {
                    return new SimpleStringProperty(cep.getLocalidade() + "/" + cep.getUf());
                });
                columnCep.setCellValueFactory(new PropertyValueFactory<>("cep"));

                tableViewCep.getItems().clear();
                tableViewCep.getItems().add(cep);
               }
    }
}
