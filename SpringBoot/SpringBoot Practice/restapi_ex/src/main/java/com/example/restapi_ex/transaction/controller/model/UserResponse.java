package com.example.restapi_ex.transaction.controller.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;


}
