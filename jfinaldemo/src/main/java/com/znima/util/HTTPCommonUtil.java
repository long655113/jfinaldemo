/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.*;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

public class HTTPCommonUtil {

    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {

                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
                    }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            // e.printStackTrace();  
        }
    }
    
    public static Connection getConn(String url, int timeout) {
        trustEveryone();
        Connection conn = HttpConnection.connect(url);
        
        return conn;
    }

    public static Object getHttpHeaders(String url, int timeout) {
        try {
            trustEveryone();
            Connection conn = HttpConnection.connect(url);
            conn.timeout(timeout);
            conn.header("Accept-Encoding", "gzip,deflate,sdch");
            conn.header("Connection", "close");
            conn.get();
            Map<String, String> result = conn.response().headers();
            result.put("title", conn.response().parse().title());
            return result;

        } catch (Exception e) {
            //e.printStackTrace();  
        }
        return null;
    }

    public static String getHtml(String url, int timeout) throws IOException {
        trustEveryone();
        Connection conn = HttpConnection.connect(url);
        conn.timeout(timeout);
        conn.header("Accept-Encoding", "gzip,deflate,sdch");
        conn.header("Connection", "close");
        Document doc = conn.get();
        String html = doc.html();
        return html;
    }

    public static void main(String[] args) throws IOException {
        try {
//            URL url = new URL("https", "https://www.xxbiquge.com/77_77263/401604.html", -1, "");

//            URL url = new URL("https://www.xxbiquge.com/77_77263/401604.html");
            
            String url = "https://www.xxbiquge.com/77_77263/401604.html";
//            System.out.println(getHttpHeaders(url, 10000));
            System.out.println(getHtml(url, 10000));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
