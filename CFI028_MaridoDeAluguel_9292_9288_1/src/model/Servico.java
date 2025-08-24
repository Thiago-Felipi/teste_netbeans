
package model;

public class Servico {
    
    int idServico;
    String tipoServico, valorHora, detalhes;

    public Servico(int idServico, String tipoServico, String valorHora, String detalhes) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.valorHora = valorHora;
        this.detalhes = detalhes;
    }

    public Servico(String tipoServico, String valorHora, String detalhes) {
        this.tipoServico = tipoServico;
        this.valorHora = valorHora;
        this.detalhes = detalhes;
    }

    

    public Servico() {
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
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

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    
    
}
