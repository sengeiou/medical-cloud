package com.zhou.medical.common.jiuzhoutong;

import com.zhou.medical.common.entity.log.JiuzhoutongLog;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class JiuzhoutongHTTPUtils {

    private final static String httpResponseEncoding = "UTF-8";
    private static Log log = LogFactory.getLog(JiuzhoutongHTTPUtils.class);
//    private static QueueProducerService queueProducerService = (QueueProducerService) SpringConfigTool.getBean("queueProducerService");
    private final static Gson gson = new Gson();


    public static String sendPostToJiuzhoutong(String url, String param, String resencoding) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        JiuzhoutongLog jiuzhoutongLog = new JiuzhoutongLog();
        try {
            log.debug("九州通接口begin=========================");
            log.debug("接口地址================================:" + url);
            log.debug("请求参数================================:" + param);
            try {
                jiuzhoutongLog.setUrl(url);
                jiuzhoutongLog.setParam(param);
                jiuzhoutongLog.setCreatetime(new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Charsert", "UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

            // 发送请求参数
//			out.print(param);
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), (resencoding == null || "".equals(resencoding.trim())
                            ? httpResponseEncoding : resencoding.trim())));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            log.debug("返回结果================================:" + result);
            log.debug("九州通接口end===========================");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                log.error("发送 POST 请求出现异常！：" + e.getMessage());
                jiuzhoutongLog.setException(e.getMessage());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                try {
                    if (StringUtils.isNotBlank(result) && result.length() > 1000) {
                        jiuzhoutongLog.setResult(result.substring(0, 1000));
                    } else {
                        jiuzhoutongLog.setResult(result);
                    }
                    jiuzhoutongLog.setUpdatetime(new Date());
//                    queueProducerService.sendJiuzhoutongMessage(gson.toJson(jiuzhoutongLog));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


}
