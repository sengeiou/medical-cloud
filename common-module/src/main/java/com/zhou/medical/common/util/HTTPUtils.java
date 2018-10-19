package com.zhou.medical.common.util;

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
    private static Log log = LogFactory.getLog(HTTPUtils.class);
    private static final int CONNECT_TIMEOUT_TIME = 15000;
    private static final int SO_TIMEOUT_TIME = 20000;

    /**
     * @param urlstr
     * @return
     */
    public static String httpDoGet(String urlstr) {
        return httpDoGet(urlstr, httpConnectTimeout, httpReadTimeout, httpResponseEncoding, httpRetryCount);
    }

    /**
     * @param urlstr
     * @param resencoding
     * @return
     */
    public static String httpDoGet(String urlstr, String resencoding) {
        return httpDoGet(urlstr, httpConnectTimeout, httpReadTimeout, resencoding, httpRetryCount);
    }

    /**
     * @param urlstr
     * @param connecttimeout
     * @param readtimeout
     * @param resencoding
     * @param retrycount
     * @return
     */
    public static String httpDoGet(String urlstr, int connecttimeout, int readtimeout, String resencoding,
                                   int retrycount) {
        String response = null;
        InputStreamReader resinput = null;
        HttpURLConnection httpurlconn = null;
        try {
            URL url = new URL(urlstr);
            httpurlconn = (HttpURLConnection) url.openConnection();
            httpurlconn.setConnectTimeout(1000 * (connecttimeout < 0 ? httpConnectTimeout : connecttimeout));
            httpurlconn.setReadTimeout(1000 * (readtimeout < 0 ? httpReadTimeout : readtimeout));
            httpurlconn.setRequestMethod("GET");

            int icode = httpurlconn.getResponseCode();
            if (icode == HttpURLConnection.HTTP_OK) {
                int length = httpurlconn.getContentLength();
                if (length <= 0) {
                    return null;
                }
                resinput = new InputStreamReader(httpurlconn.getInputStream(),
                        (resencoding == null || "".equals(resencoding.trim()) ? httpResponseEncoding
                                : resencoding.trim()));
                StringBuilder strbuf = new StringBuilder(length);
                char[] charbuff = new char[length];
                int i;
                while ((i = resinput.read(charbuff, 0, length - 1)) != -1) {
                    strbuf.append(charbuff, 0, i);
                }
                response = strbuf.toString();
                tryHttpDoGet = 0;
            } else {
                tryHttpDoGet++;
                if (tryHttpDoGet <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                    // System.out.println("------------ [Retry] HTTP DoGet
                    // TryCount : " + tryHttpDoGet);
                    return httpDoGet(urlstr, connecttimeout, readtimeout, resencoding, retrycount);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.info("url" + urlstr);
            e.printStackTrace();

            tryHttpDoGet++;
            if (tryHttpDoGet <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                return httpDoGet(urlstr, connecttimeout, readtimeout, resencoding, retrycount);
            } else {
                return null;
            }
        } finally {
            if (null != resinput) {
                try {
                    resinput.close();
                } catch (Exception e) {
                }
            }
            if (null != httpurlconn) {
                try {
                    httpurlconn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }

    /**
     * @param urlstr
     * @param params
     * @return
     */
    public static String httpDoPost(String urlstr, Map<String, String> params) {
        return httpDoPost(urlstr, null, params, httpConnectTimeout, httpReadTimeout, httpRequestEncoding,
                httpResponseEncoding, httpRetryCount);
    }

    /**
     * @param urlstr
     * @param params
     * @param reqencoding
     * @param resencoding
     * @return
     */
    public static String httpDoPost(String urlstr, Map<String, String> params, String reqencoding, String resencoding) {
        return httpDoPost(urlstr, null, params, httpConnectTimeout, httpReadTimeout, reqencoding, resencoding,
                httpRetryCount);
    }

    /**
     * @param urlstr
     * @param header
     * @param params
     * @param reqencoding
     * @param resencoding
     * @return
     */
    public static String httpDoPost(String urlstr, Map<String, String> header, Map<String, String> params,
                                    String reqencoding, String resencoding) {
        return httpDoPost(urlstr, header, params, httpConnectTimeout, httpReadTimeout, reqencoding, resencoding,
                httpRetryCount);
    }

    /**
     * @param urlstr
     * @param header
     * @param params
     * @param connecttimeout
     * @param readtimeout
     * @param reqencoding
     * @param resencoding
     * @param retrycount
     * @return
     */
    public static String httpDoPost(String urlstr, Map<String, String> header, Map<String, String> params,
                                    int connecttimeout, int readtimeout, String reqencoding, String resencoding, int retrycount) {
        String response = null;
        InputStreamReader resinput = null;
        HttpURLConnection httpurlconn = null;
        try {
            URL url = new URL(urlstr);
            httpurlconn = (HttpURLConnection) url.openConnection();
            httpurlconn.setConnectTimeout(1000 * (connecttimeout < 0 ? httpConnectTimeout : connecttimeout));
            httpurlconn.setReadTimeout(1000 * (readtimeout < 0 ? httpReadTimeout : readtimeout));
            httpurlconn.setRequestMethod("POST");
            httpurlconn.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded;charset=" + (reqencoding == null || "".equals(reqencoding.trim())
                            ? httpRequestEncoding : reqencoding.trim()));
            httpurlconn.setDoInput(true);
            httpurlconn.setDoOutput(true);
            if (null != header) {
                for (String headerKey : header.keySet()) {
                    httpurlconn.setRequestProperty(headerKey, header.get(headerKey));
                }
            }
            if (null != params) {
                StringBuilder strbuf = new StringBuilder();
                for (String key : params.keySet()) {
                    strbuf.append(key).append("=").append(params.get(key)).append("&");
                }
                String postparams = strbuf.toString();
                postparams = postparams.substring(0, postparams.length() - 1);
                // System.out.println("HTTPS Ruquest Params : " + postparams);
                byte[] data = postparams.getBytes(reqencoding);
                int length = data.length;
                httpurlconn.setRequestProperty("Content-Length", String.valueOf(length));
                if (length != 0) {
                    OutputStream resoutput = null;
                    try {
                        resoutput = httpurlconn.getOutputStream();
                        resoutput.write(data);
                        resoutput.flush();
                    } catch (Exception e) {
                        log.info("url" + urlstr);
                        e.printStackTrace();
                    } finally {
                        if (null != resoutput) {
                            try {
                                resoutput.close();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }

            int icode = httpurlconn.getResponseCode();
            if (icode == HttpURLConnection.HTTP_OK) {
                int length = httpurlconn.getContentLength();
                if (length <= 0) {
                    return null;
                }
                resinput = new InputStreamReader(httpurlconn.getInputStream(),
                        (resencoding == null || "".equals(resencoding.trim()) ? httpResponseEncoding
                                : resencoding.trim()));
                StringBuilder strbuf = new StringBuilder(length);
                char[] charbuff = new char[length];
                int i;
                while ((i = resinput.read(charbuff, 0, length - 1)) != -1) {
                    strbuf.append(charbuff, 0, i);
                }
                response = strbuf.toString();
                tryHttpDoPost = 0;
            } else {
                tryHttpDoPost++;
                if (tryHttpDoPost <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                    return httpDoPost(urlstr, header, params, connecttimeout, readtimeout, reqencoding, resencoding,
                            retrycount);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.info("url" + urlstr);
            e.printStackTrace();
            tryHttpDoPost++;
            if (tryHttpDoPost <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                return httpDoPost(urlstr, header, params, connecttimeout, readtimeout, reqencoding, resencoding,
                        retrycount);
            } else {
                return null;
            }
        } finally {
            if (null != resinput) {
                try {
                    resinput.close();
                } catch (Exception e) {
                }
            }
            if (null != httpurlconn) {
                try {
                    httpurlconn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }

    /**
     * @param urlstr
     * @param content
     * @param reqencoding
     * @param resencoding
     * @return
     */
    public static String httpDoPostStrT(String urlstr, String content, String reqencoding, String resencoding) {
        System.out.println("------------ [Start] HTTPS DoPost URL : " + urlstr);
        long t1 = System.currentTimeMillis();
        String response = null;
        InputStreamReader resinput = null;
        HttpURLConnection httpsurlconn = null;

        try {
            URL url = new URL(urlstr);
            httpsurlconn = (HttpURLConnection) url.openConnection();
            httpsurlconn.setConnectTimeout(httpConnectTimeout * 1000);
            httpsurlconn.setReadTimeout(httpReadTimeout * 1000);
            httpsurlconn.setRequestMethod("POST");
            httpsurlconn.setRequestProperty("Content-Type", "application/json; charset=" + reqencoding);// �����ļ�����

            httpsurlconn.setDoInput(true);
            httpsurlconn.setDoOutput(true);
            httpsurlconn.setRequestProperty("token", TokenEncryptUtils.sxyEncryMD5(content));
            byte[] data = content.getBytes(reqencoding);
            int dlength = data.length;
            httpsurlconn.setRequestProperty("Content-Length", String.valueOf(dlength));
            if (dlength != 0) {
                OutputStream resoutput = null;
                try {
                    resoutput = httpsurlconn.getOutputStream();
                    resoutput.write(data);
                    resoutput.flush();
                } catch (Exception e) {
                    log.info("url" + urlstr);
                    e.printStackTrace();
                } finally {
                    if (null != resoutput) {
                        try {
                            resoutput.close();
                        } catch (Exception e) {
                            System.out.println("HTTPS Ruquest Params OutputStreamWriter Close Exception " + e);
                        }
                    }
                }
            }

            int icode = httpsurlconn.getResponseCode();
            System.out.println("HTTPS Response Code : " + icode + " (" + httpsurlconn.getResponseMessage() + ") ");
            if (icode == HttpURLConnection.HTTP_OK) {
                int length = httpsurlconn.getContentLength();
                if (length <= 0) {
                    return null;
                }
                resinput = new InputStreamReader(httpsurlconn.getInputStream(),
                        (resencoding == null || "".equals(resencoding.trim()) ? httpResponseEncoding
                                : resencoding.trim()));
                StringBuilder strbuf = new StringBuilder(length);
                char[] charbuff = new char[length];
                int i;
                while ((i = resinput.read(charbuff, 0, length - 1)) != -1) {
                    strbuf.append(charbuff, 0, i);
                }
                response = strbuf.toString();
                tryHttpsDoPost = 0;
                System.out.println(
                        "HTTPS Response Content : \n------------ (" + length + ") \n" + response + "\n------------");
            } else {
                tryHttpsDoPost++;
                if (tryHttpsDoPost <= httpRetryCount) {
                    System.out.println("------------ [Retry] HTTPS DoPost TryCount : " + tryHttpsDoPost);
                    return httpDoPostStr(urlstr, content, reqencoding, resencoding);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.info("url" + urlstr);
            e.printStackTrace();
            tryHttpsDoPost++;
            if (tryHttpsDoPost <= httpRetryCount) {
                System.out.println("------------ [Retry] HTTPS DoPost TryCount : " + tryHttpsDoPost);
                return httpDoPostStrT(urlstr, content, reqencoding, resencoding);
            } else {
                return null;
            }
        } finally {
            if (null != resinput) {
                try {
                    resinput.close();
                } catch (Exception e) {
                    log.info("url" + urlstr);
                    e.printStackTrace();
                }
            }
            if (null != httpsurlconn) {
                try {
                    httpsurlconn.disconnect();
                } catch (Exception e) {
                    log.info("url" + urlstr);
                    e.printStackTrace();
                }
            }
            long t2 = System.currentTimeMillis();
            System.out.println("------------ [End] HTTPS DoPost ( " + (t2 - t1) + " ms ) ");
        }
        return response;
    }

    /**
     * @param urlstr
     * @return
     */
    public static String httpsDoGet(String urlstr) {
        return httpsDoGet(urlstr, httpConnectTimeout, httpReadTimeout, httpResponseEncoding, httpRetryCount);
    }

    /**
     * @param urlstr
     * @param resencoding
     * @return
     */
    public static String httpsDoGet(String urlstr, String resencoding) {
        return httpsDoGet(urlstr, httpConnectTimeout, httpReadTimeout, resencoding, httpRetryCount);
    }

    /**
     * @param urlstr
     * @param connecttimeout
     * @param readtimeout
     * @param resencoding
     * @param retrycount
     * @return
     */
    public static String httpsDoGet(String urlstr, int connecttimeout, int readtimeout, String resencoding,
                                    int retrycount) {
        String response = null;
        InputStreamReader resinput = null;
        HttpsURLConnection httpsurlconn = null;
        try {
            URL url = new URL(urlstr);
            httpsurlconn = (HttpsURLConnection) url.openConnection();
            httpsurlconn.setConnectTimeout(1000 * (connecttimeout < 0 ? httpConnectTimeout : connecttimeout));
            httpsurlconn.setReadTimeout(1000 * (readtimeout < 0 ? httpReadTimeout : readtimeout));
            httpsurlconn.setRequestMethod("GET");

            int icode = httpsurlconn.getResponseCode();
            if (icode == HttpURLConnection.HTTP_OK) {
                int length = httpsurlconn.getContentLength();
                if (length <= 0) {
                    return null;
                }
                resinput = new InputStreamReader(httpsurlconn.getInputStream(),
                        (resencoding == null || "".equals(resencoding.trim()) ? httpResponseEncoding
                                : resencoding.trim()));
                StringBuilder strbuf = new StringBuilder(length);
                char[] charbuff = new char[length];
                int i;
                while ((i = resinput.read(charbuff, 0, length - 1)) != -1) {
                    strbuf.append(charbuff, 0, i);
                }
                response = strbuf.toString();
                tryHttpsDoGet = 0;
            } else {
                tryHttpsDoGet++;
                if (tryHttpsDoGet <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                    return httpsDoGet(urlstr, connecttimeout, readtimeout, resencoding, retrycount);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.info("url" + urlstr);
            e.printStackTrace();
            tryHttpsDoGet++;
            if (tryHttpsDoGet <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                return httpsDoGet(urlstr, connecttimeout, readtimeout, resencoding, retrycount);
            } else {
                return null;
            }
        } finally {
            if (null != resinput) {
                try {
                    resinput.close();
                } catch (Exception e) {
                    // System.out.println("HTTPS DoGet InputStreamReader Close
                    // Exception " + e);
                }
            }
            if (null != httpsurlconn) {
                try {
                    httpsurlconn.disconnect();
                } catch (Exception e) {
                    // System.out.println("HTTPS DoGet HttpURLConnection Close
                    // Exception " + e);
                }
            }
            // long t2 = System.currentTimeMillis();
            // System.out.println("------------ [End] HTTPS DoGet ( " + (t2 -
            // t1) + " ms ) ");
        }
        return response;
    }

    /**
     * @param urlstr
     * @param params
     * @return
     */
    public static String httpsDoPost(String urlstr, Map<String, String> params) {
        return httpsDoPost(urlstr, null, params, httpConnectTimeout, httpReadTimeout, httpRequestEncoding,
                httpResponseEncoding, httpRetryCount);
    }

    /**
     * @param urlstr
     * @param params
     * @param reqencoding
     * @param resencoding
     * @return
     */
    public static String httpsDoPost(String urlstr, Map<String, String> params, String reqencoding,
                                     String resencoding) {
        return httpsDoPost(urlstr, null, params, httpConnectTimeout, httpReadTimeout, reqencoding, resencoding,
                httpRetryCount);
    }


    /**
     * @param urlstr
     * @param header
     * @param params
     * @param reqencoding
     * @param resencoding
     * @return
     */
    public static String httpsDoPost(String urlstr, Map<String, String> header, Map<String, String> params,
                                     String reqencoding, String resencoding) {
        return httpsDoPost(urlstr, header, params, httpConnectTimeout, httpReadTimeout, reqencoding, resencoding,
                httpRetryCount);
    }

    /**
     * @param urlstr
     * @param header
     * @param params
     * @param connecttimeout
     * @param readtimeout
     * @param reqencoding
     * @param resencoding
     * @param retrycount
     * @return
     */
    public static String httpsDoPost(String urlstr, Map<String, String> header, Map<String, String> params,
                                     int connecttimeout, int readtimeout, String reqencoding, String resencoding, int retrycount) {
        System.out.println("------------ [Start] HTTPS DoPost URL : " + urlstr);
        long t1 = System.currentTimeMillis();
        String response = null;
        InputStreamReader resinput = null;
        HttpsURLConnection httpsurlconn = null;
        try {
            URL url = new URL(urlstr);
            httpsurlconn = (HttpsURLConnection) url.openConnection();
            httpsurlconn.setConnectTimeout(1000 * (connecttimeout < 0 ? httpConnectTimeout : connecttimeout));
            httpsurlconn.setReadTimeout(1000 * (readtimeout < 0 ? httpReadTimeout : readtimeout));
            httpsurlconn.setRequestMethod("POST");
            httpsurlconn.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded;charset="
                            + (reqencoding = (reqencoding == null || "".equals(reqencoding.trim()) ? httpRequestEncoding
                            : reqencoding.trim())));
            httpsurlconn.setDoInput(true);
            httpsurlconn.setDoOutput(true);
            if (null != header) {
                for (String header_key : header.keySet()) {
                    httpsurlconn.setRequestProperty(header_key, header.get(header_key));
                }
            }
            if (null != params) {
                StringBuilder strbuf = new StringBuilder();
                for (String key : params.keySet()) {
                    strbuf.append(key).append("=").append(params.get(key)).append("&");
                }
                String postparams = strbuf.toString();
                postparams = postparams.substring(0, postparams.length() - 1);
                System.out.println("HTTPS Ruquest Params : " + postparams);
                byte[] data = postparams.getBytes(reqencoding);
                int length = data.length;
                httpsurlconn.setRequestProperty("Content-Length", String.valueOf(length));
                if (length != 0) {
                    OutputStream resoutput = null;
                    try {
                        resoutput = httpsurlconn.getOutputStream();
                        resoutput.write(data);
                        resoutput.flush();
                    } catch (Exception e) {
                        log.info("url" + urlstr);
                        e.printStackTrace();
                    } finally {
                        if (null != resoutput) {
                            try {
                                resoutput.close();
                            } catch (Exception e) {
                                System.out.println("HTTPS Ruquest Params OutputStreamWriter Close Exception " + e);
                            }
                        }
                    }
                }
            }

            int icode = httpsurlconn.getResponseCode();
            System.out.println("HTTPS Response Code : " + icode + " (" + httpsurlconn.getResponseMessage() + ") ");
            if (icode == HttpURLConnection.HTTP_OK) {
                int length = httpsurlconn.getContentLength();
                if (length <= 0) {
                    return null;
                }
                resinput = new InputStreamReader(httpsurlconn.getInputStream(),
                        (resencoding == null || "".equals(resencoding.trim()) ? httpResponseEncoding
                                : resencoding.trim()));
                StringBuilder strbuf = new StringBuilder(length);
                char[] charbuff = new char[length];
                int i;
                while ((i = resinput.read(charbuff, 0, length - 1)) != -1) {
                    strbuf.append(charbuff, 0, i);
                }
                response = strbuf.toString();
                tryHttpsDoPost = 0;
                System.out.println(
                        "HTTPS Response Content : \n------------ (" + length + ") \n" + response + "\n------------");
            } else {
                tryHttpsDoPost++;
                if (tryHttpsDoPost <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                    System.out.println("------------ [Retry] HTTPS DoPost TryCount : " + tryHttpsDoPost);
                    return httpsDoPost(urlstr, header, params, connecttimeout, readtimeout, reqencoding, resencoding,
                            retrycount);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            log.info("url" + urlstr);
            e.printStackTrace();
            tryHttpsDoPost++;
            if (tryHttpsDoPost <= (retrycount < 0 ? httpRetryCount : retrycount)) {
                System.out.println("------------ [Retry] HTTPS DoPost TryCount : " + tryHttpsDoPost);
                return httpsDoPost(urlstr, header, params, connecttimeout, readtimeout, reqencoding, resencoding,
                        retrycount);
            } else {
                return null;
            }
        } finally {
            if (null != resinput) {
                try {
                    resinput.close();
                } catch (Exception e) {
                    System.out.println("HTTPS DoPost InputStreamReader Close Exception " + e);
                }
            }
            if (null != httpsurlconn) {
                try {
                    httpsurlconn.disconnect();
                } catch (Exception e) {
                    System.out.println("HTTPS DoPost HttpsURLConnection Close Exception " + e);
                }
            }
            long t2 = System.currentTimeMillis();
            System.out.println("------------ [End] HTTPS DoPost ( " + (t2 - t1) + " ms ) ");
        }
        return response;
    }


    public static String httpDoPostStr(String urlstr, String content, String reqencoding, String resencoding) {
        System.out.println("------------ [Start] HTTPS DoPost URL : " + urlstr);
        long t1 = System.currentTimeMillis();
        String response = null;
        InputStreamReader resinput = null;
        HttpURLConnection httpsurlconn = null;

        try {
            URL url = new URL(urlstr);
            httpsurlconn = (HttpURLConnection) url.openConnection();
            httpsurlconn.setConnectTimeout(httpConnectTimeout * 1000);
            httpsurlconn.setReadTimeout(httpReadTimeout * 1000);
            httpsurlconn.setRequestMethod("POST");
            httpsurlconn.setRequestProperty("Content-Type", "text/xml; charset=" + reqencoding);// �����ļ�����

            httpsurlconn.setDoInput(true);
            httpsurlconn.setDoOutput(true);

            byte[] data = content.getBytes(reqencoding);
            int dlength = data.length;
            httpsurlconn.setRequestProperty("Content-Length", String.valueOf(dlength));
            if (dlength != 0) {
                OutputStream resoutput = null;
                try {
                    resoutput = httpsurlconn.getOutputStream();
                    resoutput.write(data);
                    resoutput.flush();
                } catch (Exception e) {
                    log.info("url" + urlstr);
                    e.printStackTrace();
                } finally {
                    if (null != resoutput) {
                        try {
                            resoutput.close();
                        } catch (Exception e) {
                            System.out.println("HTTPS Ruquest Params OutputStreamWriter Close Exception " + e);
                        }
                    }
                }
            }

            int icode = httpsurlconn.getResponseCode();
            System.out.println("HTTPS Response Code : " + icode + " (" + httpsurlconn.getResponseMessage() + ") ");
            if (icode == HttpURLConnection.HTTP_OK) {
                int length = httpsurlconn.getContentLength();
                if (length <= 0) {
                    return null;
                }
                resinput = new InputStreamReader(httpsurlconn.getInputStream(),
                        (resencoding == null || "".equals(resencoding.trim()) ? httpResponseEncoding
                                : resencoding.trim()));
                StringBuilder strbuf = new StringBuilder(length);
                char[] charbuff = new char[length];
                int i;
                while ((i = resinput.read(charbuff, 0, length - 1)) != -1) {
                    strbuf.append(charbuff, 0, i);
                }
                response = strbuf.toString();
                tryHttpsDoPost = 0;
                System.out.println(
                        "HTTPS Response Content : \n------------ (" + length + ") \n" + response + "\n------------");
            } else {
                tryHttpsDoPost++;
                if (tryHttpsDoPost <= httpRetryCount) {
                    System.out.println("------------ [Retry] HTTPS DoPost TryCount : " + tryHttpsDoPost);
                    return httpDoPostStr(urlstr, content, reqencoding, resencoding);
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("HTTPS DoPost Exception " + e);
            tryHttpsDoPost++;
            if (tryHttpsDoPost <= httpRetryCount) {
                log.info("url" + urlstr);
                e.printStackTrace();
                return httpDoPostStr(urlstr, content, reqencoding, resencoding);
            } else {
                return null;
            }
        } finally {
            if (null != resinput) {
                try {
                    resinput.close();
                } catch (Exception e) {
                    System.out.println("HTTPS DoPost InputStreamReader Close Exception " + e);
                }
            }
            if (null != httpsurlconn) {
                try {
                    httpsurlconn.disconnect();
                } catch (Exception e) {
                    System.out.println("HTTPS DoPost HttpsURLConnection Close Exception " + e);
                }
            }
            long t2 = System.currentTimeMillis();
            System.out.println("------------ [End] HTTPS DoPost ( " + (t2 - t1) + " ms ) ");
        }
        return response;
    }

    /**
     * @return
     */
    public static boolean initSSL() {
        if (isInitSSL) {
            return true;
        }
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{new MyX509TrustManager()}, null);

            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
            isInitSSL = false;
            return false;
        }
        isInitSSL = true;
        return true;
    }

    /**
     * @param keyStorePath
     * @param password
     * @param trustStorePath
     * @return
     */
    public static boolean initSSL(String keyStorePath, String password, String trustStorePath) {
        if (isInitSSL) {
            return true;
        }
        try {
            KeyManagerFactory keyManagerFactory = KeyManagerFactory
                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = getKeyStore(keyStorePath, password);
            if (keyStore != null) {
                keyManagerFactory.init(keyStore, password.toCharArray());
            }


            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore trustStore = getKeyStore(trustStorePath, password);
            if (trustStore != null) {
                trustManagerFactory.init(trustStore);
            }


            SSLContext context = SSLContext.getInstance("TLS");
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);


            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
        } catch (Exception e) {
            e.printStackTrace();
            isInitSSL = false;
            return false;
        }
        isInitSSL = true;
        return true;
    }

    /**
     * @param keyStorePath
     * @param password
     * @return
     */
    public static KeyStore getKeyStore(String keyStorePath, String password) {
        if (keyStorePath == null) {
            return null;
        }
        KeyStore keyStore = null;
        FileInputStream input = null;
        try {
            keyStore = KeyStore.getInstance("JKS");
            input = new FileInputStream(keyStorePath);
            keyStore.load(input, password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return keyStore;
    }


    /**
     * @param is
     * @return 读取流中的字符串，并返回
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(is, Config.STRING_ENCODING));
        } catch (UnsupportedEncodingException e1) {
            reader = null;
            e1.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while (reader != null && (line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

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

/**
 * MyX509TrustManager
 *
 * @author Administrator
 */
class MyX509TrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}

/**
 * MyHostnameVerifier
 *
 * @author Administrator
 */
class MyHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }

}
