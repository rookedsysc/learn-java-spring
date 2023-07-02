package kr.address.convert;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import kr.kakao.api.KakaoApiKey;
import okhttp3.OkHttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class KakaoAddressApi {
    public static double[] getAddressCoordinate(String title) throws IOException {
        final String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        final String API_URL = "https://dapi.kakao.com/v3/search/book";
        final OkHttpClient client = new OkHttpClient();
        final KakaoApiKey key = new KakaoApiKey();

        // URL을 생성해줌
        String requestUrl = apiUrl + "?query=" + title;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);

        // Header에 API  키를 넣어줌
        httpGet.setHeader("Authorization", "KakaoAK " + key.get());
        // request 전송
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String responseBody = null;
            try {
                responseBody = EntityUtils.toString(response.getEntity());
            } catch (ParseException e) {
                System.out.println("responseBody cant parse to String");
            }
            Gson gson = new Gson();
            // Json 형식 JsonObject 형식으로 변환
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray documents = jsonObject.getAsJsonArray("documents");
            if (((JsonArray) documents).size() > 0) {
                JsonObject document = documents.get(0).getAsJsonObject();
                double latitude = document.get("y").getAsDouble();
                double longitude = document.get("x").getAsDouble();
                return new double[]{latitude, longitude};
            } else {
                return null;
            }
        }
    }

}
