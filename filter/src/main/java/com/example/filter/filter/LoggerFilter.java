package com.example.filter.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoggerFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    // 진입 전
    log.info(">>>>> 진입 전");

//    var req = new HttpServletRequestWrapper((HttpServletRequest) request);
    // ByteArrayOutputStream라는 Stream에서 데이터를 읽어올 수 있게 해준다.
    var req = new ContentCachingRequestWrapper((HttpServletRequest) request);

    //    var res = new HttpServletResponseWrapper((HttpServletResponse) response);
    var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

    // 컨트롤러에서 작업을 처리한다.
    // ContentCachingWrapper를 이용해서 캐싱하기 위해서는 ServeletRequest / ServletResponse가 아닌
    // 위에서 생성한 Wrapper를 사용해야 한다.
    // 즉, chain.doFilter(request, response); 이런식으로 사용하면 req는 캐싱이 안된다.
    chain.doFilter(req, res);

    var reqJson = new String(req.getContentAsByteArray());
    log.info("req : {}", reqJson);
    // 여기에서 한 번 읽었기 때문에 copyBodyToResponse()를 사용해서
    // 다시 response에 덮어씌워줘야 한다.
    var resJson = new String(res.getContentAsByteArray());
    log.info("res : {}", resJson);

    // getReader()를 사용하게되면 InputStream을 이미 사용했기 때문에 Api를 요청했을 때 에러가 발생한다.
    //    var br = req.getReader(); // buffer
    //
    //    var list = br.lines().collect(Collectors.toList());
    //    list.forEach(it -> log.info("{}", it));

    res.copyBodyToResponse();

    // 진입 후
    log.info("<<<<< 리턴");
  }
}
