package kr.co.broadwave.homeservice.homedata;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="bs_sensor_data")
public class HomeData {

    @Id
    @Column(name="s_type")
    private String s_type; // 센서타입 - 온도,동작,가전,도어,공기질 등..

    @Column(name="r_data")
    private String r_data; // 센서 Raw 데이터 ( json 문자열저장)

    @Column(name="all_motion_off")
    private String all_motion_off; // 모든 모션센서가 움직임 없으면 Y, 센서타입이 동작센서일경우만 적용

    @Column(name="modify_dt")
    private LocalDateTime modify_dt; // 수정일자

}
