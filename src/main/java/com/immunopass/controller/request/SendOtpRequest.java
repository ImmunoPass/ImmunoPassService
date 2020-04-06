package com.immunopass.controller.request;

import com.immunopass.enums.IdentifierType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendOtpRequest {
    private String identifier;
    private IdentifierType identifierType;
}
