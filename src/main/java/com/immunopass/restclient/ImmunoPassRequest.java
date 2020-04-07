package com.immunopass.restclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
@Builder
public class ImmunoPassRequest {
    private String to;
    private String userStatus;
    private String token;

}
