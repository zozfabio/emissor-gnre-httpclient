package me.zozfabio.emissorgnre.loterecepcao;

import static java.util.Objects.nonNull;

public class LoteRecepcaoModel {

    /**
     * Identificador da Guia.
     */
    private String id;

    /**
     * Sigla da UF Favorecida. (Utilizar a Tabela do IBGE)
     */
    private String ufFavorecida;

    /**
     * Código da Receita.
     */
    private String codigoReceita;

    /**
     * Código do Detalhamento da Receita.
     */
    private String codigoDetalhamentoReceita;

    /**
     * Código do Produto.
     */
    private String codigoProduto;

    /**
     * Código do Tipo de Documento de Origem.
     */
    private String tipoDocumentoOrigem;

    /**
     * Número do Documento de Origem.
     */
    private String documentoOrigem;

    /**
     * Valor Original da Guia.
     */
    private String valorPrincipal;

    /**
     * Valor total da guia (valor original + encargos).
     */
    private String valorTotal;

    /**
     * Data de vencimento da guia.
     */
    private String dataVencimento;

    /**
     * Qdo for Envio:
     * 	- Data prevista de pagamento informada pelo contribuinte.
     * Qdo Retorno:
     * 	- Quando for uma validação retorna a Data prevista de pagamento informada pelo contribuinte.
     * - Quando for uma consulta da guia retorna a Data do pagamento da guia no banco.
     */
    private String dataPagamento;

    /**
     * Número do CNPJ do Contribuinte
     */
    private String emitenteCnpj;

    /**
     * Número do CPF do Contribuinte
     */
    private String emitenteCpf;

    /**
     * Inscrição Estadual do Contribuinte na UF favorecida.
     */
    private String emitenteIe;

    /**
     * Nome da firma ou a Razão Social do Contribuinte.
     */
    private String emitenteRazaoSocial;

    /**
     * Endereço do Contribuinte.
     */
    private String emitenteEndereco;

    /**
     * Código do Município de localização do Contribuinte.(Utilizar a tabela do IBGE)
     */
    private String emitenteCodigoMunicipio;

    /**
     * Código da UF do Contribuinte.
     */
    private String emitenteUf;

    /**
     * CEP do Contribuinte.
     */
    private String emitenteCep;

    /**
     * Telefone do contribuinte.
     */
    private String emitenteTelefone;

    /**
     * Número do CNPJ do Contribuinte Destinatário
     */
    private String destinatarioCnpj;

    /**
     * Número do CPF do Contribuinte Destinatário
     */
    private String destinatarioCpf;

    /**
     * Inscrição Estadual do Contribuinte na UF favorecida.
     */
    private String destinatarioIe;

    /**
     * Nome da firma ou a Razão Social do Contribuinte.
     */
    private String destinatarioRazaoSocial;

    /**
     * Código do Município de Destino.(Utilizar a tabela do IBGE)
     */
    private String destinatarioCodigoMunicipio;

    /**
     * Número do convênio.
     */
    private String convenio;

    public LoteRecepcaoModel() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUfFavorecida(String ufFavorecida) {
        this.ufFavorecida = ufFavorecida;
    }

    public void setCodigoReceita(String codigoReceita) {
        this.codigoReceita = codigoReceita;
    }

    public void setCodigoDetalhamentoReceita(String codigoDetalhamentoReceita) {
        this.codigoDetalhamentoReceita = codigoDetalhamentoReceita;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public void setTipoDocumentoOrigem(String tipoDocumentoOrigem) {
        this.tipoDocumentoOrigem = tipoDocumentoOrigem;
    }

    public void setDocumentoOrigem(String documentoOrigem) {
        this.documentoOrigem = documentoOrigem;
    }

    public void setValorPrincipal(String valorPrincipal) {
        this.valorPrincipal = valorPrincipal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setEmitenteCnpj(String emitenteCnpj) {
        this.emitenteCnpj = emitenteCnpj;
    }

    public void setEmitenteCpf(String emitenteCpf) {
        this.emitenteCpf = emitenteCpf;
    }

    public void setEmitenteIe(String emitenteIe) {
        this.emitenteIe = emitenteIe;
    }

    public void setEmitenteRazaoSocial(String emitenteRazaoSocial) {
        this.emitenteRazaoSocial = emitenteRazaoSocial;
    }

    public void setEmitenteEndereco(String emitenteEndereco) {
        this.emitenteEndereco = emitenteEndereco;
    }

    public void setEmitenteCodigoMunicipio(String emitenteCodigoMunicipio) {
        this.emitenteCodigoMunicipio = emitenteCodigoMunicipio;
    }

    public void setEmitenteUf(String emitenteUf) {
        this.emitenteUf = emitenteUf;
    }

    public void setEmitenteCep(String emitenteCep) {
        this.emitenteCep = emitenteCep;
    }

    public void setEmitenteTelefone(String emitenteTelefone) {
        this.emitenteTelefone = emitenteTelefone;
    }

    public void setDestinatarioCnpj(String destinatarioCnpj) {
        this.destinatarioCnpj = destinatarioCnpj;
    }

    public void setDestinatarioCpf(String destinatarioCpf) {
        this.destinatarioCpf = destinatarioCpf;
    }

    public void setDestinatarioIe(String destinatarioIe) {
        this.destinatarioIe = destinatarioIe;
    }

    public void setDestinatarioRazaoSocial(String destinatarioRazaoSocial) {
        this.destinatarioRazaoSocial = destinatarioRazaoSocial;
    }

    public void setDestinatarioCodigoMunicipio(String destinatarioCodigoMunicipio) {
        this.destinatarioCodigoMunicipio = destinatarioCodigoMunicipio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        doIf(() -> builder.append("<c01_UfFavorecida>").append(ufFavorecida).append("</c01_UfFavorecida>"), nonNull(ufFavorecida));
        doIf(() -> builder.append("<c02_receita>").append(codigoReceita).append("</c02_receita>"), nonNull(codigoReceita));
        doIf(() -> builder.append("<c25_detalhamentoReceita>").append(codigoDetalhamentoReceita).append("</c25_detalhamentoReceita>"), nonNull(codigoDetalhamentoReceita));
        doIf(() -> builder.append("<c26_produto>").append(codigoProduto).append("</c26_produto>"), nonNull(codigoProduto));
        doIf(() -> builder.append("<c27_tipoIdentificacaoEmitente>1</c27_tipoIdentificacaoEmitente>")
                .append("<c03_idContribuinteEmitente>")
                .append(    "<CNPJ>").append(emitenteCnpj).append("</CNPJ>")
                .append("</c03_idContribuinteEmitente>"), nonNull(emitenteCnpj));
        doIf(() -> builder.append("<c27_tipoIdentificacaoEmitente>2</c27_tipoIdentificacaoEmitente>")
                .append("<c03_idContribuinteEmitente>")
                .append(    "<CPF>").append(emitenteCpf).append("</CPF>")
                .append("</c03_idContribuinteEmitente>"), nonNull(emitenteCpf));
        doIf(() -> builder.append("<c28_tipoDocOrigem>").append(tipoDocumentoOrigem).append("</c28_tipoDocOrigem>"), nonNull(tipoDocumentoOrigem));
        doIf(() -> builder.append("<c04_docOrigem>").append(documentoOrigem).append("</c04_docOrigem>"), nonNull(documentoOrigem));
        doIf(() -> builder.append("<c06_valorPrincipal>").append(valorPrincipal).append("</c06_valorPrincipal>"), nonNull(valorPrincipal));

        doIf(() -> builder.append("<c10_valorTotal>").append(valorTotal).append("</c10_valorTotal>"), nonNull(valorTotal));
        doIf(() -> builder.append("<c14_dataVencimento>").append(dataVencimento).append("</c14_dataVencimento>"), nonNull(dataVencimento));

        doIf(() -> builder.append("<c15_convenio>").append(convenio).append("</c15_convenio>"), nonNull(convenio));
        doIf(() -> builder.append("<c16_razaoSocialEmitente>").append(emitenteRazaoSocial).append("</c16_razaoSocialEmitente>"), nonNull(emitenteRazaoSocial));
        doIf(() -> builder.append("<c17_inscricaoEstadualEmitente>").append(emitenteIe).append("</c17_inscricaoEstadualEmitente>"), nonNull(emitenteIe));
        doIf(() -> builder.append("<c18_enderecoEmitente>").append(emitenteEndereco).append("</c18_enderecoEmitente>"), nonNull(emitenteEndereco));
        doIf(() -> builder.append("<c19_municipioEmitente>").append(emitenteCodigoMunicipio).append("</c19_municipioEmitente>"), nonNull(emitenteCodigoMunicipio));
        doIf(() -> builder.append("<c20_ufEnderecoEmitente>").append(emitenteUf).append("</c20_ufEnderecoEmitente>"), nonNull(emitenteUf));
        doIf(() -> builder.append("<c21_cepEmitente>").append(emitenteCep).append("</c21_cepEmitente>"), nonNull(emitenteCep));
        doIf(() -> builder.append("<c22_telefoneEmitente>").append(emitenteTelefone).append("</c22_telefoneEmitente>"), nonNull(emitenteTelefone));
        doIf(() -> builder.append("<c34_tipoIdentificacaoDestinatario>1</c34_tipoIdentificacaoDestinatario>")
                .append("<c35_idContribuinteDestinatario>")
                .append(    "<CNPJ>").append(destinatarioCnpj).append("</CNPJ>")
                .append("</c35_idContribuinteDestinatario>"), nonNull(destinatarioCnpj));
        doIf(() -> builder.append("<c34_tipoIdentificacaoDestinatario>1</c34_tipoIdentificacaoDestinatario>")
                .append("<c35_idContribuinteDestinatario>")
                .append(    "<CPF>").append(destinatarioCpf).append("</CPF>")
                .append("</c35_idContribuinteDestinatario>"), nonNull(destinatarioCpf));
        doIf(() -> builder.append("<c36_inscricaoEstadualDestinatario>").append(destinatarioIe).append("</c36_inscricaoEstadualDestinatario>"), nonNull(destinatarioIe));
        doIf(() -> builder.append("<c37_razaoSocialDestinatario>").append(destinatarioRazaoSocial).append("</c37_razaoSocialDestinatario>"), nonNull(destinatarioRazaoSocial));
        doIf(() -> builder.append("<c38_municipioDestinatario>").append(destinatarioCodigoMunicipio).append("</c38_municipioDestinatario>"), nonNull(destinatarioCodigoMunicipio));

        doIf(() -> builder.append("<c33_dataPagamento>").append(dataPagamento).append("</c33_dataPagamento>"), nonNull(dataPagamento));
        doIf(() -> builder.append("<c42_identificadorGuia>").append(id).append("</c42_identificadorGuia>"), nonNull(id));
        return builder.toString();
    }

    private void doIf(Runnable runnable, boolean predicate) {
        if (predicate) {
            runnable.run();
        }
    }
}
