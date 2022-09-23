package br.com.exemplo.user;


import br.com.exemplo.domain.Status;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "user")
@DynamicUpdate
@SequenceGenerator(name = "g_user", sequenceName = "seq_user", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "g_user")
    @Column(name = "code", nullable = false)
    private Integer code;
    @Embedded
    private Credential credential;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

}
