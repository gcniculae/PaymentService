package com.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends BaseEntityDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
}
