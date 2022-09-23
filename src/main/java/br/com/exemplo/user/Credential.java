package br.com.exemplo.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Credential implements Serializable {

    @Column(name = "login", length = 80, columnDefinition = "CHAR(80)")
    private String login;

    @Column(name = "password", length = 70, columnDefinition = "VARCHAR(70)")
    private String password;

}
