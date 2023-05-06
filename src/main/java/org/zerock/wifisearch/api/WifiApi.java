package org.zerock.wifisearch.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;

public class WifiApi
{
    public static int totalWifiDataCount = 0;
    public static String getWifiData(int start, int end) throws IOException
    {
        // 가져와야 하는 포맷 샘플
        // http://openapi.seoul.go.kr:8088/(인증키)/xml/TbPublicWifiInfo/1/5/

        StringBuilder strURL = new StringBuilder("http://openapi.seoul.go.kr:8088");
        strURL.append("/" +  URLEncoder.encode("4f727a545062373434344246747949","UTF-8") );
        strURL.append("/" +  URLEncoder.encode("json","UTF-8") );
        strURL.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
        strURL.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8"));
        strURL.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8"));

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url(strURL.toString()).get();
        builder.addHeader("Content-type", "application/json");  // json 으로 주고 받는다

        Request request = builder.build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful())
        {
            ResponseBody body = response.body();
            String responseString = body.string();
            body.close();

            return responseString;
        }

        return null;
    }
}