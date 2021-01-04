package kr.co.broadwave.homeservice.api.watch;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2021-01-04
 * Remark :
 */
@Data
@ToString
public class WatchMapper {
    private String id;
    private String name;

    public Watch convertWatch(){
        return Watch.builder()
                .id(this.id)
                .name(this.name)
                .modify_dt(LocalDateTime.now()).build();
    }
}
