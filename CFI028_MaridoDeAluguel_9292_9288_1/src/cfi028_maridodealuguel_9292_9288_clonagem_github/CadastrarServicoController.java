/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfi028_maridodealuguel_9292_9288_clonagem_github;

import dao.PrestadorDAO;
import dao.ServicoDAO;
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
import model.Servico;

/**
 * FXML Controller class
 *
 * @author Alunos
 */
public class CadastrarServicoController implements Initializable {

    private Servico servicoSelecionado;
    private Stage stage;
    private ServicoDAO processarServico = new ServicoDAO();
    private ObservableList<Servico> obslistServico = FXCollections.observableArrayList();
    Servico servico = new Servico();
    Utilitarios u = new Utilitarios();

    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnFechar;
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
    @FXML
    private TextField txtTipoServico;
    @FXML
    private TextField txtValorHora;
    @FXML
    private TextField txtDetalhes;
    @FXML
    private TableColumn<Servico, Integer> colunaIdServico;
    @FXML
    private TableColumn<Servico, String> colunaTipoServico;
    @FXML
    private TableColumn<Servico, String> colunaValorHora;
    @FXML
    private TableColumn<Servico, String> colunaDetalhes;
    @FXML
    private TableView<Servico> tblServico;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializa a tabela
        colunaIdServico.setCellValueFactory(new PropertyValueFactory<>("idServico"));
        colunaTipoServico.setCellValueFactory(new PropertyValueFactory<>("tipoServico"));
        colunaValorHora.setCellValueFactory(new PropertyValueFactory<>("valorHora"));
        colunaDetalhes.setCellValueFactory(new PropertyValueFactory<>("detalhes"));

        carregarServico();


        txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarServico(newValue);
        });

        btnFechar.setOnAction(event -> fechar());
    }
    
    private void carregarServico() {
        obslistServico.clear();
        obslistServico.addAll(processarServico.consultarTodos());
        tblServico.setItems(obslistServico);
    }
    
    // //Metodo que filtra os servicos atraves da barra de pesquisa
    private void filtrarServico(String filtro) {
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

    //pega as informaçoes e insere elas no banco de dados
    @FXML
    private void cadastrarServico(MouseEvent event) {
        int idServico = Integer.valueOf(lblId.getText());
        String tipoServico = txtTipoServico.getText();
        String valorHora = txtValorHora.getText();
        String detalhes = txtDetalhes.getText();

        // Servico(int idServico, String tipoServico, String valorHora, String detalhes) {
        Servico servico = new Servico(idServico, tipoServico, valorHora, detalhes);
        ServicoDAO processarServico = new ServicoDAO();
        if (processarServico.salvar(servico)) {
            Utilitarios.msgInformacao("Servico cadastrado com sucesso", 0);
            lblId.setText("");
            txtTipoServico.clear();
            txtValorHora.clear();
            txtDetalhes.clear();
        } else {
            Utilitarios.msgInformacao("Erro ao cadastrar servico!", 0);
        }

    }

    
    //pega o id do servico e exclui o servico
    @FXML
    private void apagarServico(MouseEvent event) {
        int selectedIndex = tblServico.getSelectionModel().getSelectedIndex();
        servico = tblServico.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            String situacao = processarServico.excluir(servico.getIdServico());
            if (situacao.equals("Erro")) {
                u.msgInformacao("Erro ao apagar registro", 1);
            } else {
                obslistServico.remove(selectedIndex);
                limpar();
            }
        } else {
            u.msgInformacao("Nenhum item selecionado para excluir", 1);
        }

    }
    //chama o metodo limpar
    private void limparPrestador(MouseEvent event) {
        limpar();
    }
    // limpa os campos
    public void limpar() {
        lblId.setText("");
        txtTipoServico.clear();
        txtValorHora.clear();
        txtDetalhes.clear();
        txtTipoServico.requestFocus();
    }
    
    
    // atualiza as informaçoes do servico utilizando o id
    @FXML
    private void atualizarServico(MouseEvent event) {
        int idServico = Integer.valueOf(lblId.getText());
        String tipoServico = txtTipoServico.getText();
        String valorHora = txtValorHora.getText();
        String detalhes = txtDetalhes.getText();

        // Servico(int idServico, String tipoServico, String valorHora, String detalhes) {
        Servico servico = new Servico(idServico, tipoServico, valorHora, detalhes);
        ServicoDAO processarServico = new ServicoDAO();
        if (processarServico.atualizar(servico)) {
            Utilitarios.msgInformacao("prestador atualizado com sucesso", 0);
            lblId.setText("");
            txtTipoServico.clear();
            txtValorHora.clear();
            txtDetalhes.clear();
        } else {
            Utilitarios.msgInformacao("Erro ao atualizar prestador!", 0);
        }

    }
    //chama o metodo limpar
    @FXML
    private void limparServico(MouseEvent event) {
        limpar();
    }

    
    //mostra as informaçoes do servico apos clicar no text field
    @FXML
    private void tblServicoClicked(MouseEvent event) {
        
        servico = tblServico.getSelectionModel().getSelectedItem();
        if(servico != null) {
            
            servico.setIdServico(servico.getIdServico());
            lblId.setText(String.valueOf(servico.getIdServico()));
            txtTipoServico.setText(servico.getTipoServico());
            txtValorHora.setText(servico.getValorHora());
            txtDetalhes.setText(servico.getDetalhes());

        }
        
    }

}
