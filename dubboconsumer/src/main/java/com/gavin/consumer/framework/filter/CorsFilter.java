package com.gavin.consumer.framework.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.cors.*;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 17428 on 2023/9/17.
 */
@Component
public class CorsFilter extends OncePerRequestFilter {

    private final CorsConfigurationSource configSource;

    private CorsProcessor corsProcessor = new DefaultCorsProcessor();


    public CorsFilter(CorsConfigurationSource configSource) {
        Assert.notNull(configSource,"CorsConfigurationSource can not be null");
        this.configSource = configSource;
    }

    public void setCorsProcessor(CorsProcessor corsProcessor) {
        Assert.notNull(corsProcessor,"CorsProcessor can not be null");
        this.corsProcessor = corsProcessor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CorsConfiguration corsConfiguration = this.configSource.getCorsConfiguration(request);
        boolean isvalid = this.corsProcessor.processRequest(corsConfiguration, request, response);
        if (isvalid && !CorsUtils.isPreFlightRequest(request)) {
            filterChain.doFilter(request,response);
        }




    }
}
