package cn.smilex.openvas.scan.util;

import cn.smilex.openvas.scan.pojo.HashMapBuilder;
import cn.smilex.req.HttpBodyInterface;
import cn.smilex.req.HttpRequest;
import cn.smilex.req.HttpResponse;
import cn.smilex.req.Requests;

import java.util.HashMap;
import java.util.Optional;

import static cn.smilex.req.Requests.REQUEST_METHOD.GET;

/**
 * @author smilex
 * @date 2022/9/29/13:02
 * @since 1.0
 */
public final class RequestUtil {
    public static HashMap<String, String> JSON_HEADER = new HashMapBuilder<String, String>(1)
            .put("content-type", "application/json")
            .get();

    public static Optional<HttpResponse> get(
            String url
    ) {
        return Optional.of(
                Requests.requests.fast_get(url)
        );
    }

    public static Optional<HttpResponse> get(
            String url,
            HashMap<String, String> headers,
            HashMap<String, String> cookies
    ) {
        return req(GET, url, headers, cookies, null);
    }

    public static Optional<HttpResponse> req(
            Requests.REQUEST_METHOD method,
            String url,
            HashMap<String, String> headers,
            HashMap<String, String> cookies,
            HttpBodyInterface body
    ) {
        return Optional.of(
                Requests.requests.request(
                        HttpRequest.build()
                                .setUrl(url)
                                .setMethod(method)
                                .setHeaders(headers)
                                .setCookies(cookies)
                                .setBody(body)
                )
        );
    }
}
