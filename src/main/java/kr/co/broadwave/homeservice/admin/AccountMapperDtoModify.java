package kr.co.broadwave.homeservice.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Minkyu
 * Date : 2021-02-05
 * Time :
 * Remark :
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountMapperDtoModify {
    private String oldpassword;
    private String newpassword;
    private String passwordconfirm;
}
