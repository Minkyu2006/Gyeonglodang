package kr.co.broadwave.homeservice.admin;

/**
 * @author Minkyu
 * Date : 2021-02-05
 * Time :
 * Remark : 사용자 권한 구분
 */
public enum AccountRole {
    ROLE_ADMIN("ROLE_ADMIN", "시스템관리자"),;

    private final String code;
    private final String desc;

    AccountRole(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }}


