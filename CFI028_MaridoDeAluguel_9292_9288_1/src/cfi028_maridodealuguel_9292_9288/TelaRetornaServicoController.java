/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfi028_maridodealuguel_9292_9288;

import dao.PrestadorDAO;
import dao.ServicoDAO;
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
import model.Servico;

/**
 * FXML Controller class
 *
 * @author Alunos
 */
public class TelaRetornaServicoController implements Initializable {

    private Servico servicoSelecionado;
    private Stage stage;
    private ServicoDAO processarServico = new ServicoDAO();
    private ObservableList<Servico> obslistServico = FXCollections.observableArrayList();
    
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnFechar;
    @FXML
    private TableColumn<Servico, Integer> colunaIdServico;
    @FXML
    private TableColumn<Servico, String> colunaTipoServico;
    @FXML
    private TableColumn<Servico, String> colunaValorHora;
    @FXML
    private TableColumn<Servico, String> colunaDetalhe;
    @FXML
    private TableView<Servico> tblServico;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa a tabela
        colunaIdServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));
        colunaTipoServico.setCellValueFactory(new PropertyValueFactory<>("tipoServico"));
        colunaValorHora.setCellValueFactory(new PropertyValueFactory<>("valorHora"));
        colunaDetalhe.setCellValueFactory(new PropertyValueFactory<>("detalhes"));

        carregarPrestador();

        //Após clicar duas vezes em um item, chama o metodo fechar
        tblServico.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                servicoSelecionado = tblServico.getSelectionModel().getSelectedItem();
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
    //limpa o observablelist e adiciona o novos prestadores
    private void carregarPrestador() {
        obslistServico.clear();
        obslistServico.addAll(processarServico.consultarTodos());
        tblServico.setItems(obslistServico);
    }

    //Metodo que filtra os prestadores atraves da barra de pesquisa
    private void filtrarPrestador(String filtro) {
        obslistServico.clear();
        if (filtro != null && !filtro.trim().isEmpty()) {
            for (Servico servico : processarServico.consultarTodos()) {
                if (servico.getTipoServico().toUpperCase().contains(filtro.toUpperCase().trim())) {
                    obslistServico.add(servico);
                }
            }
        } else {
            obslistServico.addAll(processarServico.consultarTodos());
        }
        tblServico.setItems(obslistServico);
    }

    public Servico exibirSelecao() {
        if (stage != null) {
            stage.showAndWait();
        }
        return servicoSelecionado;
    }

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
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
