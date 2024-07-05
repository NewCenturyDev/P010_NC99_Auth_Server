package com.newcentury99.p010_nc99_auth_server.commons.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralPageableResDTO extends GeneralResDTO {
    boolean isPageable = false;
    Integer pageIdx = null;
    Integer totalPage = null;
    Long pageElementSize = null;
}
