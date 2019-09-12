package me.zozfabio.emissorgnre.emissaopdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeInter25;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

class PDFLoteGuiasGNRE {

    private static final BaseColor LINES_COLOR = BaseColor.BLACK;
    private static final BaseColor FONT_COLOR = BaseColor.BLACK;
    private static final Font LABELS_FONT = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD, FONT_COLOR);
    private static final Font VALUES_FONT = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, FONT_COLOR);
    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, FONT_COLOR);
    private static final Font LINHA_DIGITAVEL_FONT = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, FONT_COLOR);
    private static final Font IDENTIFICACAO_FONT = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, FONT_COLOR);
    private static final Font AUTENTICACAO_FONT = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL, FONT_COLOR);

    private final PDFLoteGuiasGNREFields fields;

    PDFLoteGuiasGNRE(LoteGuiasGNRE lote) {
        fields = new PDFLoteGuiasGNREFields(lote);
    }

    void addGuiasTo(Document doc, PdfWriter writer) throws DocumentException {
        while (fields.hasNext()) {
            fields.next();

            addViaInvalidaWarn(doc);
            addMotivosRejeicao(doc);
            addVia(doc, writer, "1ª via - Banco");

            addSeparadorVia(doc);

            addViaInvalidaWarn(doc);
            addMotivosRejeicao(doc);
            addVia(doc, writer, "2ª via - Contribuinte");

            if (fields.is3aViaVisivel()) {
                addViaInvalidaWarn(doc);
                addMotivosRejeicao(doc);
                addVia(doc, writer, "3ª via - Contribuinte/Fisco");
            }
        }
    }

    private void addViaInvalidaWarn(Document doc) throws DocumentException {
        if (!fields.isValida()) {
            doc.add(new Paragraph("Guia Inválida para Pagamento", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.RED)));
        }
    }

    private void addMotivosRejeicao(Document doc) throws DocumentException {
        fields.getMotivosRejeicao()
                .forEach(mr -> {
                    try {
                        doc.add(new Paragraph(mr, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.RED)));
                    } catch (DocumentException ex) {
                        throw new RuntimeException("Erro ao adicionar motivo de rejeição no documento.", ex);
                    }
                });
    }

    private void addVia(Document doc, PdfWriter writer, String identificacao) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[] {68, 30, 2});
        table.setWidthPercentage(100);
        table.setKeepTogether(true);

        // linha 1
        PdfPCell viaCell11 = getViaCell11();
        table.addCell(viaCell11);

        PdfPCell viaCell12 = getViaCell12();
        table.addCell(viaCell12);

        PdfPCell autenticacaoCell = getAutenticacaoCell();
        autenticacaoCell.setRowspan(3);
        table.addCell(autenticacaoCell);

        // linha 2
        PdfPCell viaCell21 = getViaCell21();
        table.addCell(viaCell21);

        PdfPCell viaCell22 = getViaCell22();
        table.addCell(viaCell22);

        // linha 3
        PdfPCell viaCell31 = getViaCell31();
        table.addCell(viaCell31);

        PdfPCell viaCell32 = getViaCell32();
        table.addCell(viaCell32);

        // linha 4
        PdfPCell linhaDigitavelCell = getLinhaDigitavelCell();
        table.addCell(linhaDigitavelCell);

        PdfPCell identicacaoViaCell = getIdenticacaoViaCell(identificacao);
        table.addCell(identicacaoViaCell);

        table.addCell(getEmptyCell());

        // linha 5
        PdfPCell codigoBarrasCell = getCodigoBarrasCell(writer);
        table.addCell(codigoBarrasCell);

        table.addCell(getEmptyCell());
        table.addCell(getEmptyCell());

        // fim via
        doc.add(table);
    }

    private PdfPCell getViaCell11() {
        PdfPCell cell = getTitleCell("Guia Nacional de Recolhimento de Tributos Estaduais - GNRE");
        cell.setPadding(0);
        cell.setBorder(PdfPCell.LEFT + PdfPCell.TOP + PdfPCell.BOTTOM);
        cell.setBorderWidth(1);
        cell.setBorderColor(LINES_COLOR);

        return cell;
    }

    private PdfPCell getViaCell12() {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(0);
        cell.setBorder(PdfPCell.LEFT + PdfPCell.TOP + PdfPCell.RIGHT + PdfPCell.BOTTOM);
        cell.setBorderWidth(1);
        cell.setBorderColor(LINES_COLOR);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell cellUfFavorecida = new PdfPCell();
        cellUfFavorecida.setBorder(PdfPCell.NO_BORDER);
        cellUfFavorecida.addElement(get2LinesField("UF Favorecida", fields.getUfFavorecida()));
        table.addCell(cellUfFavorecida);

        PdfPCell cellCodigoReceita = new PdfPCell();
        cellCodigoReceita.setBorder(PdfPCell.LEFT);
        cellCodigoReceita.setBorderWidth(1);
        cellCodigoReceita.setBorderColor(LINES_COLOR);
        cellCodigoReceita.addElement(get2LinesField("Codigo da Receita", fields.getCodigoReceita()));
        table.addCell(cellCodigoReceita);

        cell.addElement(table);

        return cell;
    }

    private PdfPCell getAutenticacaoCell() {
        PdfPCell cell = new PdfPCell(new Phrase("Autenticação", AUTENTICACAO_FONT));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setRotation(270);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }

    private PdfPCell getViaCell21() {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(0);
        cell.setBorder(PdfPCell.LEFT + PdfPCell.BOTTOM);
        cell.setBorderWidth(1);
        cell.setBorderColor(LINES_COLOR);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        // linha 1
        PdfPCell titleCell = getLabelCell("Dados do Contribuinte Emitente");
        titleCell.setColspan(4);
        titleCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(titleCell);

        // linha 2
        PdfPCell razaoSocialLabel = getLabelCell("Razão Social:");
        razaoSocialLabel.setColspan(3);
        table.addCell(razaoSocialLabel);

        PdfPCell docLabel = getLabelCell("CPF/CNPJ/Insc. Est.:");
        table.addCell(docLabel);

        // linha 3
        PdfPCell razaoSocialValue = getValueCell(fields.getEmitenteRazaoSocial());
        razaoSocialValue.setColspan(3);
        razaoSocialValue.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(razaoSocialValue);

        PdfPCell docValue = getValueCell(fields.getEmitenteDocumento());
        docValue.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(docValue);

        // linha 4
        PdfPCell enderecoLabel = getLabelCell("Endereço:");
        table.addCell(enderecoLabel);

        PdfPCell enderecoValue = getValueCell(fields.getEmitenteEndereco());
        enderecoValue.setColspan(3);
        enderecoValue.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(enderecoValue);

        // linha 5
        PdfPCell municipioLabel = getLabelCell("Municipio:");
        table.addCell(municipioLabel);

        PdfPCell municipioValue = getValueCell(fields.getEmitenteMunicipio());
        municipioValue.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(municipioValue);

        PdfPCell ufLabel = getLabelCell("UF:");
        ufLabel.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(ufLabel);

        PdfPCell ufValue = getValueCell(fields.getEmitenteUf());
        ufValue.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(ufValue);

        // linha 6
        PdfPCell cepLabel = getLabelCell("CEP:");
        table.addCell(cepLabel);

        PdfPCell cepValue = getValueCell(fields.getEmitenteCep());
        cepValue.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cepValue);

        PdfPCell telefoneLabel = getLabelCell("Telefone:");
        telefoneLabel.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(telefoneLabel);

        PdfPCell telefoneValue = getValueCell(fields.getEmitenteTelefone());
        telefoneValue.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(telefoneValue);

        cell.addElement(table);

        return cell;
    }

    private PdfPCell getViaCell22() {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(0);
        cell.setBorder(PdfPCell.LEFT + PdfPCell.RIGHT + PdfPCell.BOTTOM);
        cell.setBorderWidth(1);
        cell.setBorderColor(LINES_COLOR);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell nossoNumeroCell = new PdfPCell();
        nossoNumeroCell.setBorder(PdfPCell.BOTTOM);
        nossoNumeroCell.setBorderWidth(1);
        nossoNumeroCell.setBorderColor(LINES_COLOR);
        nossoNumeroCell.addElement(get2LinesField("Nº de Controle", fields.getNossoNumero()));
        table.addCell(nossoNumeroCell);

        PdfPCell dataVencimento = new PdfPCell();
        dataVencimento.setBorder(PdfPCell.BOTTOM);
        dataVencimento.setBorderWidth(1);
        dataVencimento.setBorderColor(LINES_COLOR);
        dataVencimento.addElement(get2LinesField("Data de Vencimento", fields.getDataVencimento()));
        table.addCell(dataVencimento);

        PdfPCell numeroDocumentoOrigemCell = new PdfPCell();
        numeroDocumentoOrigemCell.setBorder(PdfPCell.NO_BORDER);
        numeroDocumentoOrigemCell.addElement(get2LinesField("Nº Documento de Origem", fields.getNumeroDocumentoOrigem()));
        table.addCell(numeroDocumentoOrigemCell);

        cell.addElement(table);

        return cell;
    }

    private PdfPCell getViaCell31() {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(0);
        cell.setBorder(PdfPCell.LEFT + PdfPCell.BOTTOM);
        cell.setBorderWidth(1);
        cell.setBorderColor(LINES_COLOR);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell cell1 = new PdfPCell();
        cell1.setPadding(0);
        cell1.setBorder(PdfPCell.BOTTOM);
        cell1.setBorderWidth(1);
        cell1.setBorderColor(LINES_COLOR);
        cell1.addElement(getDadosDestinatario());
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setPadding(0);
        cell2.setBorder(PdfPCell.BOTTOM);
        cell2.setBorderWidth(1);
        cell2.setBorderColor(LINES_COLOR);
        cell2.addElement(getReservadoFiscalizacao());
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell();
        cell3.setPadding(0);
        cell3.setBorder(PdfPCell.NO_BORDER);
        cell3.addElement(getInformacoesComplementares());
        table.addCell(cell3);

        cell.addElement(table);

        return cell;
    }

    private PdfPTable getDadosDestinatario() {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell titleCell = getLabelCell("Dados do Destinatário");
        titleCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(titleCell);

        PdfPCell docCell = new PdfPCell();
        docCell.setBorder(PdfPCell.NO_BORDER);
        docCell.setPadding(0);
        docCell.addElement(get2ColumnsField("CPF/CNPJ/Insc. Est.:", fields.getDestinatarioDocumento()));
        table.addCell(docCell);

        PdfPCell municipioCell = new PdfPCell();
        municipioCell.setBorder(PdfPCell.NO_BORDER);
        municipioCell.setPadding(0);
        municipioCell.addElement(get2ColumnsField("Municipio:", fields.getDestinatarioMunicipio()));
        table.addCell(municipioCell);

        return table;
    }

    private PdfPTable getReservadoFiscalizacao() {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell titleCell = getLabelCell("Reservado a Fiscalização");
        titleCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(titleCell);

        PdfPCell convenioCell = new PdfPCell();
        convenioCell.setBorder(PdfPCell.NO_BORDER);
        convenioCell.setPadding(0);
        convenioCell.addElement(get2ColumnsField("Convênio/Protocolo:", fields.getConvenio()));
        table.addCell(convenioCell);

        PdfPCell produtoCell = new PdfPCell();
        produtoCell.setBorder(PdfPCell.NO_BORDER);
        produtoCell.setPadding(0);
        produtoCell.addElement(get2ColumnsField("Produto:", fields.getProduto()));
        table.addCell(produtoCell);

        return table;
    }

    private PdfPTable getInformacoesComplementares() {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell informacaoComplementarLabelCell = getLabelCell("Informações Complementares");
        informacaoComplementarLabelCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(informacaoComplementarLabelCell);

        PdfPCell informacaoComplementarValueCell = new PdfPCell();
        informacaoComplementarValueCell.setBorder(PdfPCell.NO_BORDER);
        informacaoComplementarValueCell.setPadding(0);
        informacaoComplementarValueCell.setMinimumHeight(60);
        informacaoComplementarValueCell.addElement(new Paragraph(fields.getInformacaoComplementar(), VALUES_FONT));
        table.addCell(informacaoComplementarValueCell);

        PdfPCell dataLimitePagamentoCell = new PdfPCell();
        dataLimitePagamentoCell.setBorder(PdfPCell.NO_BORDER);
        dataLimitePagamentoCell.setPadding(0);
        dataLimitePagamentoCell.addElement(get2ColumnsField("Documento válido para pagamento até:", fields.getDataLimitePagamento()));
        table.addCell(dataLimitePagamentoCell);

        return table;
    }

    private PdfPCell getViaCell32() {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(0);
        cell.setBorder(PdfPCell.LEFT + PdfPCell.RIGHT + PdfPCell.BOTTOM);
        cell.setBorderWidth(1);
        cell.setBorderColor(LINES_COLOR);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // linha 1
        PdfPCell periodoReferenciaCell = new PdfPCell();
        periodoReferenciaCell.setBorder(PdfPCell.BOTTOM);
        periodoReferenciaCell.setBorderWidth(1);
        periodoReferenciaCell.setBorderColor(LINES_COLOR);
        periodoReferenciaCell.addElement(get2LinesField("Período de Referência", fields.getPeriodoReferencia()));
        table.addCell(periodoReferenciaCell);

        PdfPCell parcelaCell = new PdfPCell();
        parcelaCell.setBorder(PdfPCell.LEFT + PdfPCell.BOTTOM);
        parcelaCell.setBorderWidth(1);
        parcelaCell.setBorderColor(LINES_COLOR);
        parcelaCell.addElement(get2LinesField("Parcela", fields.getParcela()));
        table.addCell(parcelaCell);

        // linha 2
        PdfPCell valorCell = new PdfPCell();
        valorCell.setBorder(PdfPCell.BOTTOM);
        valorCell.setBorderWidth(1);
        valorCell.setBorderColor(LINES_COLOR);
        valorCell.setColspan(2);
        valorCell.addElement(get2LinesField("Valor Principal", fields.getValor()));
        table.addCell(valorCell);

        // linha 3
        PdfPCell atuaizacaoMonetariaCell = new PdfPCell();
        atuaizacaoMonetariaCell.setBorder(PdfPCell.BOTTOM);
        atuaizacaoMonetariaCell.setBorderWidth(1);
        atuaizacaoMonetariaCell.setBorderColor(LINES_COLOR);
        atuaizacaoMonetariaCell.setColspan(2);
        atuaizacaoMonetariaCell.addElement(get2LinesField("Atualização Monetária", fields.getAtualizacaoMonetaria()));
        table.addCell(atuaizacaoMonetariaCell);

        // linha 4
        PdfPCell jurosCell = new PdfPCell();
        jurosCell.setBorder(PdfPCell.BOTTOM);
        jurosCell.setBorderWidth(1);
        jurosCell.setBorderColor(LINES_COLOR);
        jurosCell.setColspan(2);
        jurosCell.addElement(get2LinesField("Juros", fields.getJuros()));
        table.addCell(jurosCell);

        // linha 5
        PdfPCell multaCell = new PdfPCell();
        multaCell.setBorder(PdfPCell.BOTTOM);
        multaCell.setBorderWidth(1);
        multaCell.setBorderColor(LINES_COLOR);
        multaCell.setColspan(2);
        multaCell.addElement(get2LinesField("Multa", fields.getMulta()));
        table.addCell(multaCell);

        // linha 6
        PdfPCell totalRecolherCell = new PdfPCell();
        totalRecolherCell.setBorder(PdfPCell.NO_BORDER);
        totalRecolherCell.setColspan(2);
        totalRecolherCell.addElement(get2LinesField("Total a Recolher", fields.getTotalRecolher()));
        table.addCell(totalRecolherCell);

        cell.addElement(table);

        return cell;
    }

    private PdfPCell getLinhaDigitavelCell() {
        PdfPCell cell = new PdfPCell(new Phrase(fields.getLinhaDigitavel(), LINHA_DIGITAVEL_FONT));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        return cell;
    }

    private PdfPCell getIdenticacaoViaCell(String identificacao) {
        PdfPCell cell = new PdfPCell(new Phrase(identificacao, IDENTIFICACAO_FONT));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        return cell;
    }

    private PdfPCell getCodigoBarrasCell(PdfWriter writer) {
        BarcodeInter25 barcode = new BarcodeInter25();
        barcode.setCode(fields.getCodigoBarras());
        barcode.setExtended(true);
        barcode.setTextAlignment(Element.ALIGN_LEFT);
        barcode.setBarHeight(44.00f);
        barcode.setFont(null);
        barcode.setX(0.73f);
        barcode.setN(4);

        PdfPCell cell = new PdfPCell(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY), true);
        cell.setBorder(PdfPCell.NO_BORDER);

        return cell;
    }

    private void addSeparadorVia(Document doc) throws DocumentException {
        doc.add(new Paragraph(" "));
        doc.add(new DottedLineSeparator());
        doc.add(new Paragraph(" "));
    }

    private PdfPCell getEmptyCell() {
        PdfPCell cell = new PdfPCell(new Phrase(""));
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private PdfPCell getTitleCell(String title) {
        PdfPCell cell = new PdfPCell(new Phrase(title, TITLE_FONT));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }

    private PdfPCell getLabelCell(String label) {
        PdfPCell cell = new PdfPCell(new Phrase(label, LABELS_FONT));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }

    private PdfPCell getValueCell(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, VALUES_FONT));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return cell;
    }

    private PdfPTable get2LinesField(String label, String value) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);

        PdfPCell labelCell = getLabelCell(label);
        table.addCell(labelCell);

        PdfPCell valueCell = getValueCell(value);
        table.addCell(valueCell);

        return table;
    }

    private PdfPTable get2ColumnsField(String label, String value) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell labelCell = getLabelCell(label);
        table.addCell(labelCell);

        PdfPCell valueCell = getValueCell(value);
        valueCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(valueCell);

        return table;
    }
}
