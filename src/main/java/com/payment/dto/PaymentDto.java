package com.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class PaymentDto extends BaseEntityDto {

    @NotNull
    private Long clientId;

    @NotNull
    private Double amount;
}
