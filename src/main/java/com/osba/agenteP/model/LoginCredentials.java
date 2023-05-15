package com.osba.agenteP.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCredentials {
    @Nullable
    private String rfc;
    @Nullable
    private Integer id;
    @Nullable
    private String correo;
    @Nullable
    private Integer telefono;
    @Nullable
    private Integer nss;
    private String password;
}
