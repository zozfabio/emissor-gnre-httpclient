package me.zozfabio.emissorgnre.emissaopdf;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.nonNull;

class LoteGuiasGNRERegistro1 {

    public static final int EMITENTE_TIPO_DOCUMENTO_CPF = 1;
    public static final int EMITENTE_TIPO_DOCUMENTO_CNPJ = 2;
    public static final int EMITENTE_TIPO_DOCUMENTO_IE = 3;

    public static final int DESTINATARIO_TIPO_DOCUMENTO_CPF = 1;
    public static final int DESTINATARIO_TIPO_DOCUMENTO_CNPJ = 2;
    public static final int DESTINATARIO_TIPO_DOCUMENTO_IE = 3;

    private String id;
    private Integer sequencia;
    private String situacao;
    private String ufFavorecida;
    private Integer codigoReceita;
    private Integer guiaGeradaContigencia;
    private String reservado;

    private Integer emitenteTipoDocumento;
    private String emitenteDocumento;
    private String emitenteRazaoSocial;
    private String emitenteEndereco;
    private String emitenteMunicipio;
    private String emitenteUf;
    private String emitenteCep;
    private String emitenteTelefone;

    private Integer destinatarioTipoDocumento;
    private String destinatarioDocumento;
    private String destinatarioMunicipio;

    private String produto;
    private String numeroDocumentoOrigem;
    private String convenio;
    private String informacaoComplementar;
    private String dataVencimento;
    private String dataLimitePagamento;
    private String periodoReferencia;
    private String mesAnoReferencia;
    private Integer parcela;
    private BigDecimal valor;
    private BigDecimal atualizacaoMonetaria;
    private BigDecimal juros;
    private BigDecimal multa;
    private String representacaoNumerica;
    private String codigoBarras;
    private Integer quantidadeVias;
    private String numeroControle;

    private List<LoteGuiasGNRERegistro2> registros2 = new LinkedList<>();

    LoteGuiasGNRERegistro1(String registro) {
        sequencia = Integer.parseInt(registro.substring(1, 5));
        situacao = registro.substring(5, 6);
        ufFavorecida = registro.substring(6, 8);
        codigoReceita = Integer.parseInt(registro.substring(8, 14));

        emitenteTipoDocumento = Integer.parseInt(registro.substring(14, 15));
        emitenteDocumento = registro.substring(15, 31);
        emitenteRazaoSocial = registro.substring(31, 91);
        emitenteEndereco = registro.substring(91, 151);
        emitenteMunicipio = registro.substring(151, 201);
        emitenteUf = registro.substring(201, 203);
        emitenteCep = registro.substring(203, 211);
        emitenteTelefone = registro.substring(211, 222);

        destinatarioTipoDocumento = Integer.parseInt(registro.substring(222, 223));
        destinatarioDocumento = registro.substring(223, 239);
        destinatarioMunicipio = registro.substring(239, 289);

        produto = registro.substring(289, 544);
        numeroDocumentoOrigem = registro.substring(544, 562);
        convenio = registro.substring(562, 592);
        informacaoComplementar = registro.substring(592, 892);
        dataVencimento = registro.substring(892, 900);
        dataLimitePagamento = registro.substring(900, 908);
        periodoReferencia = registro.substring(908, 909);
        mesAnoReferencia = registro.substring(909, 915);
        parcela = Integer.parseInt(registro.substring(915, 918));
        valor = new BigDecimal(registro.substring(918, 933)).setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        atualizacaoMonetaria = new BigDecimal(registro.substring(933, 948)).setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        juros = new BigDecimal(registro.substring(948, 963)).setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        multa = new BigDecimal(registro.substring(963, 978)).setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        representacaoNumerica = registro.substring(978, 1026);
        codigoBarras = registro.substring(1026, 1070);
        quantidadeVias = Integer.parseInt(registro.substring(1070, 1071));
        numeroControle = registro.substring(1071, 1087);
        id = registro.substring(1087, 1097);
        guiaGeradaContigencia = Integer.parseInt(registro.substring(1097, 1098));
        reservado = registro.substring(1098, 1224);
    }

    Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    Optional<Integer> getSequencia() {
        return Optional.ofNullable(sequencia);
    }

    Optional<String> getSituacao() {
        return Optional.ofNullable(situacao);
    }

    Optional<String> getUfFavorecida() {
        return Optional.ofNullable(ufFavorecida);
    }

    Optional<Integer> getCodigoReceita() {
        return Optional.ofNullable(codigoReceita);
    }

    Optional<Integer> getGuiaGeradaContigencia() {
        return Optional.ofNullable(guiaGeradaContigencia);
    }

    Optional<String> getReservado() {
        return Optional.ofNullable(reservado);
    }

    Optional<Integer> getEmitenteTipoDocumento() {
        return Optional.ofNullable(emitenteTipoDocumento);
    }

    Optional<String> getEmitenteDocumento() {
        return Optional.ofNullable(emitenteDocumento)
                .flatMap(it -> getEmitenteTipoDocumento()
                        .map(tipo -> {
                            switch (tipo) {
                                case EMITENTE_TIPO_DOCUMENTO_CPF:
                                    return it.substring(it.length() - 11);
                                case EMITENTE_TIPO_DOCUMENTO_CNPJ:
                                case EMITENTE_TIPO_DOCUMENTO_IE:
                                    return it.substring(it.length() - 14);
                                default:
                                    return "";
                            }
                        }))
                .flatMap(it -> getEmitenteTipoDocumento()
                        .map(tipo -> {
                            switch (tipo) {
                                case EMITENTE_TIPO_DOCUMENTO_CPF:
                                    return it.substring(0, 3) + "." + it.substring(3, 6) + "." + it.substring(6, 9) + "-" + it.substring(9);
                                case EMITENTE_TIPO_DOCUMENTO_CNPJ:
                                    return it.substring(0, 2) + "." + it.substring(2, 5) + "." + it.substring(5, 8) + "/" + it.substring(8, 12) + "-" + it.substring(12);
                                default:
                                    return it;
                            }
                        }));
    }

    Optional<String> getEmitenteRazaoSocial() {
        return Optional.ofNullable(emitenteRazaoSocial);
    }

    Optional<String> getEmitenteEndereco() {
        return Optional.ofNullable(emitenteEndereco);
    }

    Optional<String> getEmitenteMunicipio() {
        return Optional.ofNullable(emitenteMunicipio);
    }

    Optional<String> getEmitenteUf() {
        return Optional.ofNullable(emitenteUf);
    }

    Optional<String> getEmitenteCep() {
        return Optional.ofNullable(emitenteCep)
                .map(it -> String.format("%s.%s-%s", it.substring(0, 2), it.substring(2, 5), it.substring(5)));
    }

    Optional<String> getEmitenteTelefone() {
        return Optional.ofNullable(emitenteTelefone)
                .filter(it -> !"00000000000".equals(it))
                .map(it -> {
                    String areaCode = null;
                    String number = null;

                    if (it.length() > 11) {
                        int start = it.length() - 11;
                        areaCode = it.substring(start, start + 2);
                        number = it.substring(start + 2);
                    } else if (it.length() == 11 || it.length() == 10) { // 2 digits area code + 9 or 8 digits number
                        areaCode = it.substring(0, 2);
                        number = it.substring(2);
                    } else if (it.length() == 9) { // 9 digits number
                        number = it;
                    }

                    String format = String.format("%s-%s",
                            number.substring(0,5),
                            number.substring(5));
                    if (nonNull(areaCode)) {
                        format = String.format("%s %s", areaCode, format);
                    }

                    return format;
                });
    }

    Optional<Integer> getDestinatarioTipoDocumento() {
        return Optional.ofNullable(destinatarioTipoDocumento);
    }

    Optional<String> getDestinatarioDocumento() {
        return Optional.ofNullable(destinatarioDocumento)
                .flatMap(it -> getDestinatarioTipoDocumento()
                        .map(tipo -> {
                            switch (tipo) {
                                case DESTINATARIO_TIPO_DOCUMENTO_CPF:
                                    return it.substring(it.length() - 11);
                                case DESTINATARIO_TIPO_DOCUMENTO_CNPJ:
                                case DESTINATARIO_TIPO_DOCUMENTO_IE:
                                    return it.substring(it.length() - 14);
                                default:
                                    return "";
                            }
                        }))
                .flatMap(it -> getDestinatarioTipoDocumento()
                        .map(tipo -> {
                            switch (tipo) {
                                case DESTINATARIO_TIPO_DOCUMENTO_CPF:
                                    return it.substring(0, 3) + "." + it.substring(3, 6) + "." + it.substring(6, 9) + "-" + it.substring(9);
                                case DESTINATARIO_TIPO_DOCUMENTO_CNPJ:
                                    return it.substring(0, 2) + "." + it.substring(2, 5) + "." + it.substring(5, 8) + "/" + it.substring(8, 12) + "-" + it.substring(12);
                                default:
                                    return it;
                            }
                        }));
    }

    Optional<String> getDestinatarioMunicipio() {
        return Optional.ofNullable(destinatarioMunicipio);
    }

    Optional<String> getProduto() {
        return Optional.ofNullable(produto);
    }

    Optional<String> getNumeroDocumentoOrigem() {
        return Optional.ofNullable(numeroDocumentoOrigem);
    }

    Optional<String> getConvenio() {
        return Optional.ofNullable(convenio);
    }

    Optional<String> getInformacaoComplementar() {
        return Optional.ofNullable(informacaoComplementar)
                .map(it -> it.replace("#@#", "\n"));
    }

    Optional<Date> getDataVencimento() {
        return Optional.ofNullable(dataVencimento)
                .map(it -> {
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.set(Integer.parseInt(it.substring(4, 8)), Integer.parseInt(it.substring(2, 4)) - 1, Integer.parseInt(it.substring(0, 2)));
                    return gc.getTime();
                });
    }

    Optional<Date> getDataLimitePagamento() {
        return Optional.ofNullable(dataLimitePagamento)
                .map(it -> {
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.set(Integer.parseInt(it.substring(4, 8)), Integer.parseInt(it.substring(2, 4)) - 1, Integer.parseInt(it.substring(0, 2)));
                    return gc.getTime();
                });
    }

    Optional<Integer> getPeriodoReferencia() {
        return Optional.ofNullable(periodoReferencia)
                .map(String::trim)
                .filter(it -> !it.isEmpty())
                .map(Integer::parseInt);
    }

    Optional<String> getMesAnoReferencia() {
        return Optional.ofNullable(mesAnoReferencia)
                .map(it -> it.substring(0, 2) + "/" + it.substring(2, 6));
    }

    Optional<String> getReferenciaDescricao() {
        return getPeriodoReferencia()
                .map(it -> {
                    switch(it) {
                        case 0: return "Mensal";
                        case 1: return "1a. quinzena";
                        case 2: return "2a. quinzena";
                        case 3: return "1o. decêndio";
                        case 4: return "2o. decêndio";
                        case 5: return "3o. decêndio";
                        default: return "";
                    }
                })
                .map(it -> it + getMesAnoReferencia()
                        .map(mesAno -> " - " + mesAno)
                        .orElse(""));
    }

    Optional<Integer> getParcela() {
        return Optional.ofNullable(parcela);
    }

    Optional<BigDecimal> getValor() {
        return Optional.ofNullable(valor);
    }

    Optional<BigDecimal> getAtualizacaoMonetaria() {
        return Optional.ofNullable(atualizacaoMonetaria);
    }

    Optional<BigDecimal> getJuros() {
        return Optional.ofNullable(juros);
    }

    Optional<BigDecimal> getMulta() {
        return Optional.ofNullable(multa);
    }

    BigDecimal getTotalRecolher() {
        return getValor().orElse(BigDecimal.ZERO)
                .add(getAtualizacaoMonetaria().orElse(BigDecimal.ZERO))
                .add(getJuros().orElse(BigDecimal.ZERO))
                .add(getMulta().orElse(BigDecimal.ZERO));
    }

    Optional<String> getRepresentacaoNumerica() {
        return Optional.ofNullable(representacaoNumerica);
    }

    Optional<String> getLinhaDigitavel() {
        return getRepresentacaoNumerica()
                .map(it -> it.substring(0, 11) + " " + it.substring(11, 12) + "  " + it.substring(12, 23) + " " + it.substring(23, 24) + "  " + it.substring(24, 35) + " " + it.substring(35, 36) + "  " + it.substring(36, 47) + " " + it.substring(47));
    }

    Optional<String> getCodigoBarras() {
        return Optional.ofNullable(codigoBarras);
    }

    Optional<String> getNossoNumero() {
        return getCodigoBarras()
                .map(it -> it.substring(26, 42));
    }

    Optional<Integer> getQuantidadeVias() {
        return Optional.ofNullable(quantidadeVias);
    }

    Optional<String> getNumeroControle() {
        return Optional.ofNullable(numeroControle);
    }

    void addRegistro2(LoteGuiasGNRERegistro2 registro2) {
        registros2.add(registro2);
    }

    List<LoteGuiasGNRERegistro2> getRegistros2() {
        return unmodifiableList(registros2);
    }
}
