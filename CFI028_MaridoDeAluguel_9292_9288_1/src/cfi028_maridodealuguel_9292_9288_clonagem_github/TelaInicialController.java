/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfi028_maridodealuguel_9292_9288_clonagem_github;

import dao.ListaDAO;
import dao.PrestadorDAO;
import dao.ServicoDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Lista;
import model.Prestador;
import model.Servico;

/**
 *
 * @author Alunos
 */
public class TelaInicialController implements Initializable {

    private Label label;
    @FXML
    private Button btnCadastrar2;
    @FXML
    private Button btnApagar2;
    @FXML
    private Button btnAtualizar2;
    @FXML
    private Button btnLimpar2;
    @FXML
    private TextField txtServico;
    @FXML
    private TextField txtNomePrestador;

    @FXML
    private Button btnCadastrarPrestador;
    @FXML
    private Button btnCadastrarServico;

    private final ListaDAO processarLista = new ListaDAO();
    private final PrestadorDAO processarPrestador = new PrestadorDAO();
    private final ServicoDAO processarServico = new ServicoDAO();

    private Lista lista = new Lista();
    private Prestador prestador = new Prestador();
    private Servico servico = new Servico();

    Utilitarios u = new Utilitarios();

    ObservableList<Lista> obslistLista = FXCollections.observableArrayList();
    ObservableList<Prestador> obslistPrestador = FXCollections.observableArrayList();
    ObservableList<Servico> obslistServico = FXCollections.observableArrayList();

    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private Label lblIdLista;
    @FXML
    private TableView<Lista> tblLista;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabela(); // Configura a tabela
        criarListeners();    // Cria os atalhos e dicas
        limpar();
        lblIdLista.requestFocus(); // Define foco inicial
    }

    public void inicializarTabela() {
        //Criação da tabela 
        TableColumn<Lista, Integer> colunaIdLista = new TableColumn<>("IdLista");
        TableColumn<Lista, String> colunaNome = new TableColumn<>("nome");
        TableColumn<Lista, String> colunaTelefone = new TableColumn<>("telefone");
        TableColumn<Lista, Double> colunaPontuacao = new TableColumn<>("pontuacao");
        TableColumn<Lista, String> colunaTipoServico = new TableColumn<>("tipoServico");
        TableColumn<Lista, String> colunaValorHora = new TableColumn<>("valorHora");

        //  Define de onde os dados são buscados na classe Viagens
        colunaIdLista.setCellValueFactory(new PropertyValueFactory<>("idLista"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaPontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        colunaTipoServico.setCellValueFactory(new PropertyValueFactory<>("tipoServico"));
        colunaValorHora.setCellValueFactory(new PropertyValueFactory<>("valorHora"));

        //  Configura a tabela
        tblLista.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblLista.getColumns().addAll(colunaIdLista, colunaNome, colunaTelefone, colunaPontuacao, colunaTipoServico, colunaValorHora);

        //  Carrega dados do banco
        obslistLista = processarLista.consultarTodos();
        tblLista.setItems(obslistLista);
    }

    //  Limpa os campos da tela
    public void limpar() {
        lblIdLista.setText("");
        txtNomePrestador.clear();
        txtServico.clear();
        lblIdLista.requestFocus();
    }

    public void reinicializarDados() {
        obslistLista.clear();
        obslistLista = processarLista.consultarTodos();
        tblLista.setItems(obslistLista);
    }

    // metodo que cria um novo stage de Prestador e abre, usando uma tela FXML
    public void chamaPrestador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaRetornaPrestador.fxml"));
            Parent root = loader.load();
            TelaRetornaPrestadorController controller = loader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Seleção de Prestador");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            prestador = controller.getPrestadorSelecionado();
            txtNomePrestador.setText(prestador.getNome());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo que cria um novo stage de Servico e abre, usando uma tela FXML
    public void chamaServico() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaRetornaServico.fxml"));
            Parent root = loader.load();
            TelaRetornaServicoController controller = loader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Seleção de Servico");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            servico = controller.getServicoSelecionado();
            txtServico.setText(servico.getTipoServico());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Cria os atalhos de F1 e as dicas (tooltips)
    public void criarListeners() {

        //  F1 no campo prestador → abre a tela de prestador
        txtNomePrestador.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.F1) {
                chamaPrestador();
            }
        });

        //  F1 no campo serviço → abre a tela de serviço
        txtServico.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.F1) {
                chamaServico();
            }
        });

        //  Tooltips (dicas)
        txtNomePrestador.setTooltip(new Tooltip("Pressione F1 para selecionar prestador"));
        txtServico.setTooltip(new Tooltip("Pressione F1 para selecionar serviço"));

    }

    @FXML
    private void handlebtnCadastrarAction(ActionEvent event) {
        try {
            //cadastra usando os ids
            if (prestador != null && servico != null) {
                processarLista.salvar(prestador.getIdPrestador(), servico.getIdServico());
            }
        } catch (Exception e) {
            u.msgInformacao("Erro ao cadastrar na lista", 0);
        }
        reinicializarDados();
    }

    //Exclui um item da tabela utilizando o idLista
    @FXML
    private void handlebtnApagarAction(ActionEvent event) {
        int selectedIndex = tblLista.getSelectionModel().getSelectedIndex();
        Lista itemSelecionado = tblLista.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0 && itemSelecionado != null) {
            processarLista.excluir(itemSelecionado.getIdLista());

        } else {
            u.msgInformacao("Nenhum item selecionado para excluir", 1);
        }
        reinicializarDados();

    }

    //Atualiza um item da tabela utilizando o idLista
    @FXML
    private void handlebtnAtualizarAction(ActionEvent event) {
        int selectedIndex = tblLista.getSelectionModel().getSelectedIndex();
        Lista itemSelecionado = tblLista.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0 && itemSelecionado != null) {
            // Atualiza o banco de dados
            lista.setIdLista(itemSelecionado.getIdLista());
            lista.setIdPrestador(prestador.getIdPrestador());
            lista.setIdServico(servico.getIdServico());

            processarLista.atualizar(lista);
        } else {
            u.msgInformacao("Nenhum item selecionado para atualizar", 1);
        }
        reinicializarDados();

    }
    
    //Chama o metodo limpar
    @FXML
    private void handlebtnLimparAction(ActionEvent event) {
        limpar();
    }

    //Chama a tela de cadastrar Prestador
    @FXML
    private void handlebtnCadastrarPrestadorAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastrarPrestador.fxml"));
        Parent root = fxmlLoader.load();
        Stage cadastroStage = new Stage();
        cadastroStage.initStyle(StageStyle.UTILITY);
        cadastroStage.setTitle("Cadastro de Prestador");
        cadastroStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        cadastroStage.initOwner(btnCadastrarPrestador.getScene().getWindow());
        cadastroStage.setScene(new Scene(root));
        cadastroStage.centerOnScreen();
        cadastroStage.showAndWait();
    }

    //Chama a tela de cadastrar Servico
    @FXML
    private void handlebtnCadastrarServicoAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CadastrarServico.fxml"));
        Parent root = fxmlLoader.load();
        Stage cadastroStage = new Stage();
        cadastroStage.initStyle(StageStyle.UTILITY);
        cadastroStage.setTitle("Cadastro de Servico");
        cadastroStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        cadastroStage.initOwner(btnCadastrarServico.getScene().getWindow());
        cadastroStage.setScene(new Scene(root));
        cadastroStage.centerOnScreen();
        cadastroStage.showAndWait();
    }

    //Adiciona as informações da tabela nos textFilds
    @FXML
    private void tblServicosClicked(MouseEvent event) {
        lista = tblLista.getSelectionModel().getSelectedItem();
        if (lista != null) {
            prestador.setIdPrestador(lista.getIdPrestador());
            txtNomePrestador.setText(lista.getNome());

            servico.setIdServico(lista.getIdServico());
            txtServico.setText(lista.getTipoServico());
            lblIdLista.setText(String.valueOf(lista.getIdLista()));

        }
    }

}
