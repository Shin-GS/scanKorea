package com.scankorea.server.controller.api;

import com.scankorea.server.common.code.SuccessCode;
import com.scankorea.server.common.response.CustomResponseBuilder;
import com.scankorea.server.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckApi {
    private final CustomResponseBuilder responseBuilder;

    @GetMapping("/api/health-check")
    public Response<Void> healthCheck() {
        return responseBuilder.of(SuccessCode.SUC);
    }
}
