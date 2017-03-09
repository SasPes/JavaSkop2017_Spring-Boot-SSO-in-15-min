package com.saspes.proxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulHeaders;
import com.netflix.zuul.context.RequestContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.util.StringUtils;

public class ForwardHeaderFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Logger.getLogger(ForwardHeaderFilter.class.getName()).log(Level.INFO, String.format("Before filter ['%s': '%s', '%s': '%s', '%s': '%s']",
                ZuulHeaders.X_FORWARDED_PROTO.toLowerCase(),
                ctx.getZuulRequestHeaders().get(ZuulHeaders.X_FORWARDED_PROTO.toLowerCase()),
                "X-Forwarded-Host",
                ctx.getZuulRequestHeaders().get("x-forwarded-host"),
                "X-Forwarded-Port",
                ctx.getZuulRequestHeaders().get("x-forwarded-port")));

        final String originalXForwardedProto = ctx.getRequest().getHeader(ZuulHeaders.X_FORWARDED_PROTO.toLowerCase());
        final String originalXForwardedPort = ctx.getRequest().getHeader("x-forwarded-port");
        final String originalXForwardedHost = ctx.getRequest().getHeader("x-forwarded-host");

        if (!StringUtils.isEmpty(originalXForwardedProto)) {
            ctx.addZuulRequestHeader(ZuulHeaders.X_FORWARDED_PROTO.toLowerCase(), originalXForwardedProto);
        }

        if (!StringUtils.isEmpty(originalXForwardedPort)) {
            ctx.addZuulRequestHeader("x-forwarded-port", originalXForwardedPort);
        }
        
        if (!StringUtils.isEmpty(originalXForwardedHost)) {
            ctx.addZuulRequestHeader("x-forwarded-host", originalXForwardedHost);
        }

        Logger.getLogger(ForwardHeaderFilter.class.getName()).log(Level.INFO, String.format("After filter ['%s': '%s', '%s': '%s', '%s': '%s']",
                ZuulHeaders.X_FORWARDED_PROTO.toLowerCase(),
                ctx.getZuulRequestHeaders().get(ZuulHeaders.X_FORWARDED_PROTO.toLowerCase()),
                "X-Forwarded-Host",
                ctx.getZuulRequestHeaders().get("x-forwarded-host"),
                "X-Forwarded-Port",
                ctx.getZuulRequestHeaders().get("x-forwarded-port")));

        return null;
    }
}
