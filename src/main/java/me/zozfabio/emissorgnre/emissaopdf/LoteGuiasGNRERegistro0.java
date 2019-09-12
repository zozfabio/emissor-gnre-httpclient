package me.zozfabio.emissorgnre.emissaopdf;

class LoteGuiasGNRERegistro0 {

    public static final int AMBIENTE_PRODUCAO = 0;
    public static final int AMBIENTE_OUTRO = 9;

    public static final int SOLICITANTE_TIPO_CPF = 1;
    public static final int SOLICITANTE_TIPO_CNPJ = 2;
    public static final int SOLICITANTE_TIPO_IE = 3;

    private int ambiente;

    private int solicitanteTipo;

    private String solicitante;

    private String loteNumero;

    LoteGuiasGNRERegistro0(String registro) {
        solicitanteTipo = Integer.parseInt(registro.substring(1, 2));
        solicitante = registro.substring(2, 16);
        loteNumero = registro.substring(16, 26);
        ambiente = Integer.parseInt(registro.substring(26, 27));
    }

    public int getAmbiente() {
        return ambiente;
    }

    public int getSolicitanteTipo() {
        return solicitanteTipo;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public String getLoteNumero() {
        return loteNumero;
    }
}
