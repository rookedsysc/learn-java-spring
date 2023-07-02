package kr.book.search;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kr.kakao.api.KakaoApiBase;
import kr.kakao.api.KakaoApiKey;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class KakaoBookApi {

    public static List<Book> searchBooks(String title) throws IOException {
        // url 생성
        KakaoApiBase kakaoApiBase = new KakaoApiBase();
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(kakaoApiBase.getBookApiUrl()).newBuilder();
        urlBuilder.addQueryParameter("query", title);

        // Authorization 헤더에 API 키를 넣어줌
        Request request = new Request.Builder().url(urlBuilder.build()).addHeader("Authorization", "KakaoAK " + new KakaoApiKey().get()).build();

        // 연결
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Request failed : " + response);
            JsonObject jsonResponse = gson.fromJson(response.body().charStream(), JsonObject.class);
            // Json 데이터 중에서 documents 데이터만 추출
            JsonArray documents = jsonResponse.getAsJsonArray("documents");

            List<Book> books = new ArrayList<>();
            for (JsonElement document : documents) {
                 // document는 JsonObject 형식이므로 Gson으로 변환
                 JsonObject bookJson = document.getAsJsonObject();
                 // 이렇게 하면 Authors가 Array로 들어오기 때문에 모델이랑 맞지 않아서 Error 발생
                 // Book book = gson.fromJson(document, Book.class);
                 Book book = new Book(
                 bookJson.get("title").getAsString(),
                 bookJson.get("authors").getAsJsonArray().toString(),
                 bookJson.get("publisher").getAsString(),
                 bookJson.get("thumbnail").getAsString()
                 );
                books.add(book);


            }
            return books;
        }
    }
}
