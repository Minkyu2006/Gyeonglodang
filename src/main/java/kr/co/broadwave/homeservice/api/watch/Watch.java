package kr.co.broadwave.homeservice.api.watch;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2021-01-04
 * Remark :
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="bs_watch")
public class Watch {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="modify_dt")
    private LocalDateTime modify_dt;
}
