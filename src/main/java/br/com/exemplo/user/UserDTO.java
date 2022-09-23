package br.com.exemplo.user;

import br.com.exemplo.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserDTO {

    private Integer code;

    @NotNull
    @Length(min = 3, message = "name invalid!")
    private String name;

    @NotNull
    @Email(message = "e-mail invalid!")
    private String email;

    @NotNull
    private Status status;

}
