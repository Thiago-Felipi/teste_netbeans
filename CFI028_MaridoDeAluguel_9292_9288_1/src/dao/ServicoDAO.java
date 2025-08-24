package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Servico;

public class ServicoDAO {

    //int idServico;
    //String tipoServico, valorHora, detalhes;
    public boolean salvar(Servico servico) {
        String sql = "INSERT INTO servico (tipoServico, valorHora, detalhes) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.criarConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getTipoServico());
            stmt.setString(2, servico.getValorHora());
            stmt.setString(3, servico.getDetalhes());

            System.out.println(stmt.toString());

            stmt.executeUpdate();
            System.out.println("Servico cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Servico: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Servico servico) {
        String sql = "UPDATE servico SET tipoServico=?,valorHora=?,detalhes=? "
                + " WHERE idServico = ?";

        try (Connection conexao = Conexao.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, servico.getTipoServico());
            ps.setString(2, servico.getValorHora());
            ps.setString(3, servico.getDetalhes());
            ps.setInt(4, servico.getIdServico());

            System.out.println("Comando atualizar  " + ps.toString());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados do servico. Metodo ATUALIZAR");
            return false;
        }
    }

    public String excluir(int id) {
        System.out.println("id " + id);
        String sql = "DELETE FROM servico WHERE idServico= ?";
        try (Connection conexao = Conexao.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            System.out.println("Comando de apagar  " + ps.toString());
            ps.executeUpdate();
            System.out.println("Registro excluido com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir dados. Metodo Excluir");
        }
        return ("Sucesso");
    }

    public ObservableList<Servico> consultarTodos() {
        String sql = "SELECT * from servico";
        ObservableList<Servico> listaServico = FXCollections.observableArrayList();
        try (Connection conexao = Conexao.criarConexao();
                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                int idServico = rs.getInt("idServico");
                String tipoServico = rs.getString("tipoServico");
                String valorHora = rs.getString("valorHora");
                String detalhes = rs.getString("detalhes");
                //int idServico;
                //String tipoServico, valorHora, detalhes;
                Servico s = new Servico(idServico, tipoServico, valorHora, detalhes);
                // System.out.println(v.toString());
                listaServico.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar banco de dados Servico. Metodo ConsultarTodos");
            return null;
        }
        return listaServico;
    }
}
