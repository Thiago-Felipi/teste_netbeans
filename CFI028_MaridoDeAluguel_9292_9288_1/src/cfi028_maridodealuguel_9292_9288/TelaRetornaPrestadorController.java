/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfi028_maridodealuguel_9292_9288;

import dao.PrestadorDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class TelaRetornaPrestadorController implements Initializable {

    private Prestador prestadorSelecionado;
    private Stage stage;
    private PrestadorDAO processarPrestador = new PrestadorDAO();
    private ObservableList<Prestador> obslistPrestador = FXCollections.observableArrayList();
    
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


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa a tabela
        colunaIdPrestador.setCellValueFactory(new PropertyValueFactory<>("idPrestador"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaeEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaPontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));

        carregarPrestador();

        //Após clicar duas vezes em um item, chama o metodo fechar
        tblPrestador.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                prestadorSelecionado = tblPrestador.getSelectionModel().getSelectedItem();
                fechar();
            }
        });

        //Chama o metodo para filtar os prestadores
        txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarPrestador(newValue);
        });

        //Ao clicar no botão fechar, chama o metodo fechar
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
}
