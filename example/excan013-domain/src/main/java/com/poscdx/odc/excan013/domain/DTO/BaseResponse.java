package com.poscdx.odc.excan013.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BaseResponse<T>{
    private boolean isSuccess;
    private int statusCode;
    private T data;
}
