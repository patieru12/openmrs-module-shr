package org.openmrs.module.basicmodule;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class CustomRestTemplate {

    public static RestTemplate createRestTemplate() {
        try {
            TrustManager[] trustAllCertificates = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }

                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            HostnameVerifier trustAllHostnames = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);

            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setBufferRequestBody(false);

            return new RestTemplate(requestFactory);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create RestTemplate with ignoring certificate validation", e);
        }
    }
}
