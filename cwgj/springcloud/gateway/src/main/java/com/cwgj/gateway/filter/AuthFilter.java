package com.cwgj.gateway.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import com.cwgj.gateway.handler.AuthHandler;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public class AuthFilter extends ZuulFilter {

  private final static Logger logger = LoggerFactory.getLogger("AuthFilter");

  @Autowired
  AuthHandler authHandler;

  @Value("#{'${sys.noFilterUrl.urls}'.split(',')}")
  private List<String> noFilterUrl;

  @Value("#{'${sys.noFilterIp.ips}'.split(',')}")
  private List<String> noFilterIp;

  @Value("${sys.isDev}")
  private boolean isDev;

  @Override
  public String filterType() {
    return PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return PRE_DECORATION_FILTER_ORDER - 1;
  }

  @Override
  public boolean shouldFilter() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    logger.info(request.getRemoteAddr());
    for (String url : noFilterUrl) {
      if (url.equals(request.getRequestURI())) {
        return false;
      }
    }
    for (String ip : noFilterIp) {
      if (ip.equals(request.getRemoteAddr())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Object run() throws ZuulException {

    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    logger.info(request.getRequestURI());
    if (!isDev) {
      String token = request.getHeader("token");
      if (StringUtils.isEmpty(token)) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        return null;
      }
      if (!authHandler.vaild(token, request.getRequestURI())) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        return null;
      }
    }
    return null;
  }
}
