package kr.kakao.api;

public class KakaoApiBase {
    private final String addrUrl = "https://dapi.kakao.com/v2/local/search/address.json";
    private final String bookUrl = "https://dapi.kakao.com/v3/search/book";

    public String getAddrUrl() {
        return addrUrl;
    }

    public String getBookApiUrl() {
        return bookUrl;
    }
}
