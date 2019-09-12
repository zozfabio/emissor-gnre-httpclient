package me.zozfabio.emissorgnre.configuf;

public class ConfigUfModel {

    public static final String AMBIENTE_PRODUCAO = "1";
    public static final String AMBIENTE_HOMOLOGACAO = "2";

    private final String ambiente;

    private final String uf;

    private final String receita;

    public ConfigUfModel(String ambiente, String uf, String receita) {
        this.ambiente = ambiente;
        this.uf = uf;
        this.receita = receita;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("<ambiente>").append(ambiente).append("</ambiente>")
                .append("<uf>").append(uf).append("</uf>")
                .append("<receita>").append(receita).append("</receita>")
                .toString();
    }
}
