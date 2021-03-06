package easemob.server.comm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 *
 */
public class HTTPUtils {
    public static String httpRequestEncoding = "UTF-8";
    public static String httpResponseEncoding = "UTF-8";
    public static int httpConnectTimeout = 60;
    public static int httpReadTimeout = 60;
    public static int httpRetryCount = 1;
    private static int tryHttpDoGet = 0;
    private static int tryHttpsDoGet = 0;
    private static int tryHttpDoPost = 0;
    private static int tryHttpsDoPost = 0;
    private static boolean isInitSSL = false;
    private static final String DES_KEY = "12345678";
    private static final int CONNECT_TIMEOUT_TIME = 15000;
    private static final int SO_TIMEOUT_TIME = 20000;

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String resencoding) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
                    (resencoding == null || "".equals(resencoding.trim()) ? httpResponseEncoding : resencoding.trim())));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}

