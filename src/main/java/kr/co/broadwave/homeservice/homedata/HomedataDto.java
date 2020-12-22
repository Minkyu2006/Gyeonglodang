package kr.co.broadwave.homeservice.homedata;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class HomedataDto {
    private String s_type; // 센서타입 - 온도,동작,가전,도어,공기질 등..
    private String r_data; // 센서 Raw 데이터 ( json 문자열저장)
    private String all_motion_off; // 모든 모션센서가 움직임 없으면 Y, 센서타입이 동작센서일경우만 적용
    private LocalDateTime modify_dt; // 수정일자

    public String getS_type() {
        return s_type;
    }

    public String getR_data() {
        return r_data;
    }

    public String getAll_motion_off() {
        return all_motion_off;
    }

    public LocalDateTime getModify_dt() {
        return modify_dt;
    }
}
