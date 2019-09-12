package me.zozfabio.emissorgnre.resultadolote;

public class ResultadoLoteModel {

    public static final String AMBIENTE_PRODUCAO = "1";
    public static final String AMBIENTE_HOMOLOGACAO = "2";

    private final String ambiente;

    private final String numeroRecibo;

    public ResultadoLoteModel(String ambiente, String numeroRecibo) {
        this.ambiente = ambiente;
        this.numeroRecibo = numeroRecibo;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("<ambiente>").append(ambiente).append("</ambiente>")
                .append("<numeroRecibo>").append(numeroRecibo).append("</numeroRecibo>")
                .toString();
    }
}
