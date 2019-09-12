package me.zozfabio.emissorgnre.emissaopdf;

class LoteGuiasGNRERegistro2 {

    private int sequencia;
    private String numeroCampo;
    private int codigoMotivoRejeicao;
    private String descricaoMotivoRejeicao;

    LoteGuiasGNRERegistro2(String registro) {
        sequencia = Integer.parseInt(registro.substring(1, 5));
        numeroCampo = registro.substring(5, 35);
        codigoMotivoRejeicao = Integer.parseInt(registro.substring(35, 38));
        descricaoMotivoRejeicao = registro.substring(38, 393);
    }

    public int getSequencia() {
        return sequencia;
    }

    public String getNumeroCampo() {
        return numeroCampo;
    }

    public int getCodigoMotivoRejeicao() {
        return codigoMotivoRejeicao;
    }

    public String getDescricaoMotivoRejeicao() {
        return descricaoMotivoRejeicao;
    }
}
