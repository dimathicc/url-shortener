package com.dimathicc.ens.urlshortener.client;

import com.dimathicc.ens.urlshortener.config.context.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignUserInterceptor implements RequestInterceptor {
    private final UserContext userContext;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("x-user-id", String.valueOf(userContext.getUserId()));
    }
}
