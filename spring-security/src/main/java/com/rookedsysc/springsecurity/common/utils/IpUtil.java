package com.rookedsysc.springsecurity.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IpUtil {
  public static String getIpAddr() {
    String ip_addr = null;
    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpServletRequest request = sra.getRequest();

    ip_addr = request.getHeader("X-Forwarded-For");
    if (ip_addr == null) {
      ip_addr = request.getHeader("Proxy-Client-IP");
    }
    if (ip_addr == null) {
      ip_addr = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip_addr == null) {
      ip_addr = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip_addr == null) {
      ip_addr = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip_addr == null) {
      ip_addr = request.getRemoteAddr();
    }
    return ip_addr;
  }
}
