package br.com.exemplo.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CredentialDTO {

    @NotNull
    public String login;
    @NotNull
    public String password;

}
