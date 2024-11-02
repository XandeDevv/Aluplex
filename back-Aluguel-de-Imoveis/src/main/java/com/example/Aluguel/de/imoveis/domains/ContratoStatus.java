package com.example.Aluguel.de.imoveis.domains;

public enum ContratoStatus {
    DESABILITADO("desabilitado"),
    PENDENTE("pendente"),
    EM_ANDAMENTO("em_andamento");
    private String status;

    ContratoStatus(String status) {
        this.status=status;
    }
    public String getContratoStatus(){
        return status;
    }
}
