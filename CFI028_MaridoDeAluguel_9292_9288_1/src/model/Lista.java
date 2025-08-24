
package model;


public class Lista {
    
    int idLista, idPrestador, idServico;
    String nome, telefone, valorHora, tipoServico;
    Double pontuacao;

    public Lista(int idLista, int idPrestador, int idServico) {
        this.idLista = idLista;
        this.idPrestador = idPrestador;
        this.idServico = idServico;
        
    }

    public Lista(int idLista, String nome, String telefone, Double pontuacao, String tipoServico, String valorHora) {
        this.idLista = idLista;
        this.nome = nome;
        this.telefone = telefone;
        this.pontuacao = pontuacao;
        this.tipoServico = tipoServico;
        this.valorHora = valorHora;
    }

    

    public Lista() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getValorHora() {
        return valorHora;
    }

    public void setValorHora(String valorHora) {
        this.valorHora = valorHora;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
    
    private String detalhes;

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    
    
}
