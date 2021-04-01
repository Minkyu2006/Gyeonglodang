package kr.co.broadwave.homeservice.lock;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 민규
 * Date : 2020-04-01
 * Time :
 * Remark : LockState
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="bs_lock")
public class Lock {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="state")
    private String state;

}
