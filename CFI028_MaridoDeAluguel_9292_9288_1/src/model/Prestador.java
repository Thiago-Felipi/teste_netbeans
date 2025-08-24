
package model;


public class Prestador {
    
    int idPrestador;
    String nome, endereco, telefone;
    Double pontuacao;

    public Prestador(int idPrestador, String nome, String endereco, String telefone, Double pontuacao) {
        this.idPrestador = idPrestador;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.pontuacao = pontuacao;
    }

    public Prestador(String nome, String endereco, String telefone, Double pontuacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.pontuacao = pontuacao;
    }
    
    

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    

    public Prestador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString() {
        return "Prestador{" + "nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone +  ", pontuacao=" + pontuacao + '}';
    }
    
    
    
}
