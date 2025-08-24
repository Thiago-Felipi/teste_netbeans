/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfi028_maridodealuguel_9292_9288_clonagem_github;

import dao.PrestadorDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Prestador;

/**
 * FXML Controller class
 *
 * @author Alunos
 */
public class CadastrarPrestadorController implements Initializable {

    private Prestador prestadorSelecionado;
    private Stage stage;
    private PrestadorDAO processarPrestador = new PrestadorDAO();
    private ObservableList<Prestador> obslistPrestador = FXCollections.observableArrayList();
    Prestador prestador = new Prestador();
    Utilitarios u = new Utilitarios();

    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnFechar;
    @FXML
    private TableColumn<Prestador, String> colunaNome;
    @FXML
    private TableColumn<Prestador, Integer> colunaIdPrestador;
    @FXML
    private TableColumn<Prestador, String> colunaeEndereco;
    @FXML
    private TableColumn<Prestador, String> colunaTelefone;
    @FXML
    private TableColumn<Prestador, Double> colunaPontuacao;
    @FXML
    private TableView<Prestador> tblPrestador;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtPontuacao;
    @FXML
    private Label lblId;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnApagar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnLimpar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaIdPrestador.setCellValueFactory(new PropertyValueFactory<>("idPrestador"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaeEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaPontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));

        carregarPrestador();


        txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarPrestador(newValue);
        });

        btnFechar.setOnAction(event -> fechar());
    }

    private void carregarPrestador() {
        obslistPrestador.clear();
        obslistPrestador.addAll(processarPrestador.consultarTodos());
        tblPrestador.setItems(obslistPrestador);
    }
    
     //Metodo que filtra os prestadores atraves da barra de pesquisa
    private void filtrarPrestador(String filtro) {
        obslistPrestador.clear();
        if (filtro != null && !filtro.trim().isEmpty()) {
            for (Prestador prestador : processarPrestador.consultarTodos()) {
                if (prestador.getNome().toUpperCase().contains(filtro.toUpperCase().trim())) {
                    obslistPrestador.add(prestador);
                }
            }
        } else {
            obslistPrestador.addAll(processarPrestador.consultarTodos());
        }
        tblPrestador.setItems(obslistPrestador);
    }

    public Prestador exibirSelecao() {
        if (stage != null) {
            stage.showAndWait();
        }
        return prestadorSelecionado;
    }

    public Prestador getPrestadorSelecionado() {
        return prestadorSelecionado;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void fechar() {
        if (stage != null) {
            stage.close();
        } else {
            // Fecha pela referência da própria janela, como fallback
            Stage janela = (Stage) btnFechar.getScene().getWindow();
            janela.close();
        }
    }

    //cadastra os prestadores 
    @FXML
    private void cadastrarPrestador(MouseEvent event) {

        int idPrestador = Integer.valueOf(lblId.getText());
        String nome = txtNome.getText();
        String endereco = txtEndereco.getText();
        String telefone = txtTelefone.getText();
        Double pontuacao = Double.valueOf(txtPontuacao.getText());

        // Prestador(int idPrestador, String nome, String endereco, String telefone, Double pontuacao) {
        Prestador prestador = new Prestador(idPrestador, nome, endereco, telefone, pontuacao);
        PrestadorDAO processarPrestador = new PrestadorDAO();
        if (processarPrestador.salvar(prestador)) {
            Utilitarios.msgInformacao("prestador cadastrado com sucesso", 0);
            lblId.setText("");
            txtNome.clear();
            txtEndereco.clear();
            txtTelefone.clear();
            txtPontuacao.clear();
        } else {
            Utilitarios.msgInformacao("Erro ao cadastrar prestador!", 0);
        }

    }
    //apaga o prestador pelo id
    @FXML
    private void apagarPrestador(MouseEvent event) {
        int selectedIndex = tblPrestador.getSelectionModel().getSelectedIndex();
        prestador = tblPrestador.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            String situacao = processarPrestador.excluir(prestador.getIdPrestador());
            if (situacao.equals("Erro")) {
                u.msgInformacao("Erro ao apagar registro", 1);
            } else {
                obslistPrestador.remove(selectedIndex);
                limpar();
            }
        } else {
            u.msgInformacao("Nenhum item selecionado para excluir", 1);
        }
    }
    //atualiza o prestador
    @FXML
    private void atualizarPrestador(MouseEvent event) {
        int idPrestador = Integer.valueOf(lblId.getText());
        String nome = txtNome.getText();
        String endereco = txtEndereco.getText();
        String telefone = txtTelefone.getText();
        Double pontuacao = Double.valueOf(txtPontuacao.getText());

        // Prestador(int idPrestador, String nome, String endereco, String telefone, Double pontuacao) {
        Prestador prestador = new Prestador(idPrestador, nome, endereco, telefone, pontuacao);
        PrestadorDAO processarPrestador = new PrestadorDAO();
        if (processarPrestador.atualizar(prestador)) {
            Utilitarios.msgInformacao("prestador atualizado com sucesso", 0);
            lblId.setText("");
            txtNome.clear();
            txtEndereco.clear();
            txtTelefone.clear();
            txtPontuacao.clear();
        } else {
            Utilitarios.msgInformacao("Erro ao atualizar prestador!", 0);
        }
    }
    
    //chama o metodo de limpar prestador
    @FXML
    private void limparPrestador(MouseEvent event) {
        limpar();
    }
    
    //limpa os todos os campos
    public void limpar() {
        lblId.setText("");
        txtNome.clear();
        txtEndereco.clear();
        txtTelefone.clear();
        txtPontuacao.clear();
        txtNome.requestFocus();
    }
    
    //quando clica na tabela preenche todos os campos com as informaçoes do prestador
    @FXML
    private void tblPrestadorClicked(MouseEvent event) {
        prestador = tblPrestador.getSelectionModel().getSelectedItem();
        if(prestador != null) {
            
            prestador.setIdPrestador(prestador.getIdPrestador());
            lblId.setText(String.valueOf(prestador.getIdPrestador()));
            txtNome.setText(prestador.getNome());
            txtEndereco.setText(prestador.getEndereco());
            txtTelefone.setText(prestador.getTelefone());
            txtPontuacao.setText(String.valueOf(prestador.getPontuacao()));

        }
    }

}
