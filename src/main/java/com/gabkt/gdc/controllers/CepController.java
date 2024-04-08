package com.gabkt.gdc.controllers;

import com.gabkt.gdc.model.Cep;
import com.gabkt.gdc.model.Cliente;
import com.gabkt.gdc.services.AlertsServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.time.LocalDate;
import java.awt.Desktop;

import static com.gabkt.gdc.services.RegexService.removeSpecialCharacters;

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
    private TextField txtComplemento;
    @FXML
    private Button btnPath;
    @FXML
    private Label pathLabel;
    @FXML
    private Button btnRegister;
    @FXML
    private ImageView imageView;
    @FXML
    private void submitCepAction(){
            String cepNumber = removeSpecialCharacters(inputCep.getText());
            String url = String.format("https://viacep.com.br/ws/%s/json/", cepNumber);
            RestTemplate restTemplate = new RestTemplate();
            Cep cep = restTemplate.getForObject(url, Cep.class);
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

        if(selectedFile != null && selectedFile.isFile() && selectedFile.getName().toLowerCase().endsWith(".txt")){
            pathLabel.setText("Bloco selecionado: " + selectedFile.getAbsolutePath());
        } else {
            Alert alert = AlertsServices.createAlertError("Erro", "Erro ao selecionar seu bloco de notas");
            alert.showAndWait();
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
            Desktop.getDesktop().open(selectedFile);        
        } catch (FileNotFoundException e) {
            Alert alerta = AlertsServices.createAlertError("Erro","Por favor selecione um Bloco de Notas");
            alerta.showAndWait();
        } catch (IOException e){
            Alert alert = AlertsServices.createAlertError("Erro", e.getMessage());
            alert.showAndWait();
        }
    }

    private String formatNote(Cliente cliente){
        String rua = "";
        String bairro= "";
        String cidadeES= "";
        for (Cep obj : tableViewCep.getItems()) {
            rua = columnRua.getCellData(obj);
            bairro = columnBairro.getCellData(obj);
            cidadeES = columnCidadeES.getCellData(obj);
        }
        StringBuilder bloco  = new StringBuilder();
        bloco.append("\n");
        bloco.append("Nome: ").append(cliente.getNome()).append("\n");
        bloco.append("Telefone 1: ").append(cliente.getTelefone1()).append("\n");
        bloco.append("Telefone 2: ").append(cliente.getTelefone2()).append("\n");
        bloco.append("Email: ").append(cliente.getEmail()).append("\n");
        bloco.append("Dia da Instalação: ").append(cliente.getDiaInstala()).append("\n");
        bloco.append("Dia do Vencimento: ").append(cliente.getDiaVenci()).append("\n");
        bloco.append("\n");
        bloco.append("CPF: ").append(cliente.getCpf()).append("\n");
        bloco.append("RG: ").append(cliente.getRg()).append("\n");
        bloco.append("Data de Nascimento: ").append(cliente.getDataNasc()).append("\n");
        bloco.append("Mae: ").append(cliente.getMae()).append("\n");
        bloco.append("\n");
        bloco.append("Cep: ").append(inputCep.getText()).append("\n");
        bloco.append("Numero/Complemento: ").append(cliente.getComplemento()).append("\n");
        bloco.append("Rua: ").append(rua).append("\n");
        bloco.append("Bairro: ").append(bairro).append("\n");
        bloco.append("Cidade/Estado: ").append(cidadeES).append("\n");
        bloco.append("HP: ").append("\n");
        bloco.append("\n");
        bloco.append("Proposta: ").append("\n");
        bloco.append("Contrato: ").append("\n");
        LocalDate localDate = LocalDate.now();
        bloco.append("Data da Venda: ").append(localDate).append("\n");
        bloco.append("Status da Venda: ").append("\n");
        bloco.append("-------------------------------------------------------------------------").append("\n");

        return bloco.toString();
    }
    public void initialize() throws IOException {
        Image image = new Image("pikachu.gif");
        imageView.setImage(image);
    }
}
