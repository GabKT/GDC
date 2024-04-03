package com.gabkt.gdc.controllers;

import com.gabkt.gdc.model.Cep;
import com.gabkt.gdc.model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

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
    private TextField txtDiaInstala;
    @FXML
    private TextField txtDiaVenci;
    @FXML
    private TextField txtTelefone1;
    @FXML
    private TextField txtTelefone2;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtDataNasc;
    @FXML
    private TextField txtMae;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtRG;
    @FXML
    private TextArea txtComplemento;
    @FXML
    private Button btnPath;
    @FXML
    private Label pathLabel;
    @FXML Button btnRegister;
    @FXML
    private void submitCepAction(){
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
    @FXML
    private void chooseFile(){
        Stage stage = (Stage) btnPath.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("titulo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt"),
                new FileChooser.ExtensionFilter("all", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null){
            System.out.println("Arquivo: " + selectedFile.getAbsolutePath());
            pathLabel.setText("Bloco selecionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("nothing selected");
        }
    }
    @FXML
    public void registerForm(){
        String labelPath = pathLabel.getText();
        String useless = "Bloco selecionado: ";
        String path = labelPath.replace(useless, "");
        File selectedFile = new File(path);
        Cliente cliente = new Cliente(txtDiaInstala.getText(), txtDiaVenci.getText(), txtTelefone1.getText(), txtTelefone2.getText(), txtEmail.getText(), txtNome.getText(), txtDataNasc.getText(), txtMae.getText(), txtCPF.getText(), txtRG.getText(), txtComplemento.getText());
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath(), true))){
            String bloco =formatNote(cliente);
            bufferedWriter.write(bloco);
                    txtDiaInstala.clear();
                    txtDiaVenci.clear();
                    txtTelefone1.clear();
                    txtTelefone2.clear();
                    txtEmail.clear();
                    txtNome.clear();
                    txtDataNasc.clear();
                    txtMae.clear();
                    txtCPF.clear();
                    txtRG.clear();
                    txtComplemento.clear();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());;
        }
    }

    private static String formatNote(Cliente cliente){
        StringBuilder bloco  = new StringBuilder();
        bloco.append("Nome: ").append(cliente.getNome()).append("\n");
        bloco.append("CPF: ").append(cliente.getCpf()).append("\n");
        bloco.append("RG: ").append(cliente.getRg()).append("\n");
        bloco.append("Data de Nascimento: ").append(cliente.getDataNasc()).append("\n");
        bloco.append("Mae: ").append(cliente.getMae()).append("\n");
        bloco.append("\n");
        bloco.append("Telefone 1: ").append(cliente.getTelefone1()).append("\n");
        bloco.append("Telefone 2: ").append(cliente.getTelefone2()).append("\n");
        bloco.append("Email: ").append(cliente.getEmail()).append("\n");
        bloco.append("Dia da Instalação: ").append(cliente.getDiaInstala()).append("\n");
        bloco.append("Dia do Vencimento: ").append(cliente.getDiaVenci()).append("\n");
        bloco.append("\n");
        bloco.append("Cep: ").append("").append("\n");
        bloco.append("Complemento: ").append(cliente.getComplemento()).append("\n");
        bloco.append("HP: ").append("\n");
        bloco.append("Proposta: ").append("\n");
        bloco.append("Contrato: ").append("\n");
        LocalDate localDate = LocalDate.now();
        bloco.append("Data da venda: ").append(localDate).append("\n");
        bloco.append("-------------------------------------------------------------------------").append("\n");

        return bloco.toString();
    }
}
