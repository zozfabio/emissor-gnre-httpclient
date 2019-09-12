package me.zozfabio.emissorgnre.loterecepcao;

import me.zozfabio.emissorgnre.ClientFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

public class LoteRecepcaoApplication {

    private static final String SERVICE_URL = "https://www.testegnre.pe.gov.br/gnreWS/services/GnreLoteRecepcao";
    private static final String SERVICE_ACTION = "https://www.testegnre.pe.gov.br/gnreWS/services/GnreLoteRecepcao/processar";

    private static final String VERSAO = "1.00";

    public static void main(String[] args) {
        LoteRecepcaoModel model = new LoteRecepcaoModel();
        model.setUfFavorecida("MG");
        model.setCodigoReceita("100056");
        model.setEmitenteCnpj("75729006000101");
        model.setTipoDocumentoOrigem("04");
        model.setDocumentoOrigem("1314834209");
        model.setValorPrincipal("1.00");
        model.setDataVencimento(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        model.setEmitenteRazaoSocial("EMITENTE TESTE");
        model.setEmitenteEndereco("EDERECO EMITENTE TESTE");
        model.setEmitenteCodigoMunicipio("06200");
        model.setEmitenteUf("MG");
        model.setEmitenteCep("38540970");
        model.setDataPagamento(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

        String requestEnvelope = getRequestEnvelope(model);
        System.out.println(requestEnvelope);

        try (CloseableHttpClient httpCLient = requireNonNull(ClientFactory.getInstance(), "Erro ao gerar HttpClient.")) {
            HttpPost httpPost = new HttpPost(SERVICE_URL);
            httpPost.setHeader(new BasicHeader("Content-Type", "application/soap+xml;charset=UTF-8"));
            httpPost.setHeader(new BasicHeader("SOAPAction", SERVICE_ACTION));
            httpPost.setEntity(new StringEntity(requestEnvelope, "UTF-8"));

            try (CloseableHttpResponse response = httpCLient.execute(httpPost)) {
                String responseEnvelope = EntityUtils.toString(response.getEntity());
                System.out.println(responseEnvelope);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String getRequestEnvelope(LoteRecepcaoModel model) {
        return new StringBuilder()
                .append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:gnr=\"http://www.gnre.pe.gov.br/webservice/GnreLoteRecepcao\">")
                .append(    "<soap:Header>")
                .append(        "<gnr:gnreCabecMsg>")
                .append(            "<gnr:versaoDados>").append(VERSAO).append("</gnr:versaoDados>")
                .append(        "</gnr:gnreCabecMsg>")
                .append(    "</soap:Header>")
                .append(    "<soap:Body>")
                .append(        "<gnr:gnreDadosMsg>")
                .append(            "<TLote_GNRE xmlns=\"http://www.gnre.pe.gov.br\">")
                .append(                "<guias>")
                .append(                    "<TDadosGNRE>").append(model.toString()).append("</TDadosGNRE>")
                .append(                "</guias>")
                .append(            "</TLote_GNRE>")
                .append(        "</gnr:gnreDadosMsg>")
                .append(    "</soap:Body>")
                .append("</soap:Envelope>")
                .toString();
    }
}
