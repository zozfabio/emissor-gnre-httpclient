package me.zozfabio.emissorgnre.emissaopdf;

class LoteGuiasGNRERegistro9 {

    private String loteNumero;
    private int totalGuias;
    private String hashValidacao;

    LoteGuiasGNRERegistro9(String registro) {
        loteNumero = registro.substring(1, 11);
        totalGuias = Integer.parseInt(registro.substring(11, 15));
        hashValidacao = registro.substring(15, 79);
    }

    public String getLoteNumero() {
        return loteNumero;
    }

    public int getTotalGuias() {
        return totalGuias;
    }

    public String getHashValidacao() {
        return hashValidacao;
    }
}
