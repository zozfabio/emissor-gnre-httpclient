package me.zozfabio.emissorgnre.configuf;

import me.zozfabio.emissorgnre.ClientFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class ConfigUFApplication {

    private static final String SERVICE_URL = "https://www.testegnre.pe.gov.br/gnreWS/services/GnreConfigUF";
    private static final String SERVICE_ACTION = "https://www.testegnre.pe.gov.br/gnreWS/services/GnreConfigUF/consultar";

    private static final String AMBIENTE = ConfigUfModel.AMBIENTE_HOMOLOGACAO;

    private static final String VERSAO = "1.00";

    public static void main(String[] args) {
        ConfigUfModel model = new ConfigUfModel(AMBIENTE, "MG", "100048");

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

    private static String getRequestEnvelope(ConfigUfModel model) {
        return new StringBuilder()
                .append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:gnr=\"http://www.gnre.pe.gov.br/webservice/GnreConfigUF\">")
                .append(    "<soap:Header>")
                .append(        "<gnr:gnreCabecMsg>")
                .append(            "<gnr:versaoDados>").append(VERSAO).append("</gnr:versaoDados>")
                .append(        "</gnr:gnreCabecMsg>")
                .append(    "</soap:Header>")
                .append(    "<soap:Body>")
                .append(        "<gnr:gnreDadosMsg>")
                .append(            "<TConsultaConfigUf xmlns=\"http://www.gnre.pe.gov.br\">").append(model.toString()).append("</TConsultaConfigUf>")
                .append(        "</gnr:gnreDadosMsg>")
                .append(    "</soap:Body>")
                .append("</soap:Envelope>")
                .toString();
    }
}
