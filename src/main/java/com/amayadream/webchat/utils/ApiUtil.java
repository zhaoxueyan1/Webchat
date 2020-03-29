package com.amayadream.webchat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class ApiUtil {
    private final static String CHARSET = "UTF-8";
    private static String httpRequest(String requestUrl) {
        //buffer用于接受返回的字符
        StringBuilder buffer = new StringBuilder();
        try {
            //建立URL，把请求地址给补全，其中urlencode（）方法用于把params里的参数给取出来
            URL url = new URL(requestUrl);
            //打开http连接
            //System.out.println(url.toString());
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            //获得输入
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //将bufferReader的值给放到buffer里
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            //关闭bufferReader和输入流
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            //断开连接
            httpUrlConn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回字符串
        return buffer.toString();
    }

    public static String sign(String s, String key, String method) throws Exception {
        Mac mac = Mac.getInstance(method);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(CHARSET), mac.getAlgorithm());
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(s.getBytes(CHARSET));
        return DatatypeConverter.printBase64Binary(hash);
    }

    public static String getStringToSign(TreeMap<String, Object> params) {
        StringBuilder s2s = new StringBuilder("GETnlp.tencentcloudapi.com/?");
        // 签名时要求对参数进行字典排序，此处用TreeMap保证顺序
        for (String k : params.keySet()) {
            s2s.append(k).append("=").append(params.get(k).toString()).append("&");
        }
        return s2s.toString().substring(0, s2s.length() - 1);
    }

    public static String getUrl(TreeMap<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder("https://nlp.tencentcloudapi.com/?");
        // 实际请求的url中对参数顺序没有要求
        for (String k : params.keySet()) {
            // 需要对请求串进行urlencode，由于key都是英文字母，故此处仅对其value进行urlencode
            url.append(k).append("=").append(URLEncoder.encode(params.get(k).toString(), CHARSET)).append("&");
        }
        return url.toString().substring(0, url.length() - 1);
    }

    static public JSONArray getJSONArray(String content) throws Exception {
        TreeMap<String, Object> params = new TreeMap<String, Object>(); // TreeMap可以自动排序
        // 实际调用时应当使用随机数，例如：params.put("Nonce", new Random().nextInt(java.lang.Integer.MAX_VALUE));
        params.put("Nonce", 11886); // 公共参数
        // 实际调用时应当使用系统当前时间，例如：
        params.put("Timestamp", System.currentTimeMillis() / 1000);
        //params.put("Timestamp", 1585388444); // 公共参数
        params.put("SecretId", "AKIDrVtFNF0JxMVNCsEUXWHlz9UaQ0DE2Twb"); // 公共参数
        params.put("Action", "KeywordsExtraction"); // 公共参数
        params.put("Version", "2019-04-08"); // 公共参数
        params.put("Region", "ap-guangzhou"); // 公共参数
        params.put("Text", content); // 公共参数
        params.put("Signature", sign(getStringToSign(params), "bw1qujIadS1JOlvrZmX5NTItDHkncBT0", "HmacSHA1")); // 公共参数
        //System.out.println();
        return JSON.parseObject(httpRequest(getUrl(params))).getJSONObject("Response").getJSONArray("Keywords");
    }
    static public ArrayList<String> getKeywords(String content) throws Exception {
        JSONArray a = getJSONArray(content);
        ArrayList<String> res = new ArrayList<>();
        a.forEach(e-> {
            String t= (String)((JSONObject) e).get("Word");
            res.add(t);
        });
        for (int i=res.size();i<5;i++){
            res.add("none");
        }
        return res;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(getKeywords("你的恶名从爱尔兰到契丹无人不止无人不晓"));

    }
}
