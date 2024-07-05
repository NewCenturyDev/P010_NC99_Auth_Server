package com.newcentury99.p010_nc99_auth_server.commons.http;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;

public class GeneralPageableReqDTO {
    @PositiveOrZero(message = "valid.page.positive")
    private Integer pageIdx;

    @PositiveOrZero(message = "valid.page.positive")
    @Max(value = 5000, message = "valid.page.maximum")
    private Integer pageLimit;
}
