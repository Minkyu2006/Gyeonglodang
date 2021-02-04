package kr.co.broadwave.homeservice.admin;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Minkyu
 * Date : 2021-02-04
 * Time :
 * Remark : 사용자비밀번호
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="bs_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(unique = true,name="user_id")
    private String userid;

    @Column(name="user_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private AccountRole role;

    @Column(name="insert_date")
    private LocalDateTime insertDateTime;

    @Column(name="modify_date")
    private LocalDateTime modifyDateTime;

}
