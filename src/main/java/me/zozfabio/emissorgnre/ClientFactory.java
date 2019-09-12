package me.zozfabio.emissorgnre;

import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class ClientFactory {

    private static final String CERTIFICADO_CLIENTE_PATH = "";
    private static final String CERTIFICADO_CLIENTE_PASSWD = "";

    private static final String KEYSTORE_SERVICO_PATH = "";
    private static final String KEYSTORE_SERVICO_PASSDW = "";

    public static CloseableHttpClient getInstance() {
        try (FileInputStream certificadoClienteIS = new FileInputStream(new File(CERTIFICADO_CLIENTE_PATH));
             FileInputStream keyStoreServicoIS = new FileInputStream(KEYSTORE_SERVICO_PATH)) {
            KeyStore keyStoreCliente = KeyStore.getInstance("PKCS12");
            keyStoreCliente.load(certificadoClienteIS, CERTIFICADO_CLIENTE_PASSWD.toCharArray());

            KeyStore keyStoreServico = KeyStore.getInstance("JKS");
            keyStoreServico.load(keyStoreServicoIS, KEYSTORE_SERVICO_PASSDW.toCharArray());

            SSLContext sslContext =  SSLContexts.custom()
                    .loadKeyMaterial(keyStoreCliente, CERTIFICADO_CLIENTE_PASSWD.toCharArray())
                    .loadTrustMaterial(keyStoreServico, new TrustAllStrategy())
                    .build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier());

            return HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
        } catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
