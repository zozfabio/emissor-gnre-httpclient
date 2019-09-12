package me.zozfabio.emissorgnre.emissaopdf;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

class PDFLoteGuiasGNREFields {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static final NumberFormat decimalFormat = NumberFormat.getCurrencyInstance();

    private final LoteGuiasGNRE lote;

    private final Iterator<LoteGuiasGNRERegistro1> guias;

    private LoteGuiasGNRERegistro1 guia;

    PDFLoteGuiasGNREFields(LoteGuiasGNRE lote) {
        this.lote = lote;
        this.guias = lote.getRegistros1().iterator();
    }

    boolean hasNext() {
        return this.guias.hasNext();
    }

    void next() {
        guia = this.guias.next();
    }

    List<String> getMotivosRejeicao() {
        return guia.getRegistros2()
                .stream()
                .map(reg -> reg.getCodigoMotivoRejeicao() + " - " + reg.getDescricaoMotivoRejeicao())
                .collect(toList());
    }

    boolean isValida() {
        return lote.getRegistro0()
                .map(reg -> reg.getAmbiente() == LoteGuiasGNRERegistro0.AMBIENTE_PRODUCAO)
                .orElse(false);
    }

    boolean is3aViaVisivel() {
        return guia.getQuantidadeVias()
                .map(it -> it >= 3)
                .orElse(false);
    }

    String getUfFavorecida() {
        return guia.getUfFavorecida()
                .orElse("");
    }

    String getParcela() {
        return guia.getParcela()
                .map(Number::toString)
                .orElse("");
    }

    String getPeriodoReferencia() {
        return guia.getReferenciaDescricao()
                .orElse("");
    }

    String getCodigoReceita() {
        return guia.getCodigoReceita()
                .map(Number::toString)
                .orElse("");
    }

    String getNossoNumero() {
        return guia.getNossoNumero()
                .orElse("");
    }

    String getNumeroDocumentoOrigem() {
        return guia.getNumeroDocumentoOrigem()
                .orElse("");
    }

    String getDataVencimento() {
        return guia.getDataVencimento()
                .map(dateFormat::format)
                .orElse("");
    }

    String getDataLimitePagamento() {
        return guia.getDataLimitePagamento()
                .map(dateFormat::format)
                .orElse("");
    }

    String getValor() {
        return guia.getValor()
                .map(decimalFormat::format)
                .orElse("");
    }

    String getMulta() {
        return guia.getMulta()
                .map(decimalFormat::format)
                .orElse("");
    }

    String getJuros() {
        return guia.getJuros()
                .map(decimalFormat::format)
                .orElse("");
    }

    String getAtualizacaoMonetaria() {
        return guia.getAtualizacaoMonetaria()
                .map(decimalFormat::format)
                .orElse("");
    }

    String getTotalRecolher() {
        return decimalFormat.format(guia.getTotalRecolher());
    }

    String getConvenio() {
        return guia.getConvenio()
                .orElse("");
    }

    String getProduto() {
        return guia.getProduto()
                .orElse("");
    }

    String getCodigoBarras() {
        return guia.getCodigoBarras()
                .orElse("");
    }

    String getLinhaDigitavel() {
        return guia.getLinhaDigitavel()
                .orElse("");
    }

    String getInformacaoComplementar() {
        return guia.getInformacaoComplementar()
                .orElse("");
    }

    String getEmitenteEndereco() {
        return guia.getEmitenteEndereco()
                .orElse("");
    }

    String getEmitenteMunicipio() {
        return guia.getEmitenteMunicipio()
                .orElse("");
    }

    String getEmitenteRazaoSocial() {
        return guia.getEmitenteRazaoSocial()
                .orElse("");
    }

    String getEmitenteDocumento() {
        return guia.getEmitenteDocumento()
                .orElse("");
    }

    String getEmitenteCep() {
        return guia.getEmitenteCep()
                .orElse("");
    }

    String getEmitenteTelefone() {
        return guia.getEmitenteTelefone()
                .orElse("");
    }

    String getEmitenteUf() {
        return guia.getEmitenteUf()
                .orElse("");
    }

    String getDestinatarioDocumento() {
        return guia.getDestinatarioDocumento()
                .orElse("");
    }

    String getDestinatarioMunicipio() {
        return guia.getDestinatarioMunicipio()
                .orElse("");
    }
}
