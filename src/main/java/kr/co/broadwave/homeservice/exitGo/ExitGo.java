package kr.co.broadwave.homeservice.exitGo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author 민규
 * Date : 2020-12-24
 * Time :
 * Remark : 퇴실엔티티
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="bs_leave")
public class ExitGo {

    @Id
    @Column(name="id")
    private String id; // 1개의 row 만 관리('pkvalue' - 고정)

    @Column(name="leave_request")
    private String leaveRequest;

    @Column(name="pre_leave_request")
    private String preLeaveRequest;

    @Column(name="modify_dt")
    private LocalDateTime modify_dt;

    @Column(name="pre_modify_dt")
    private LocalDateTime pre_modify_dt;

}
