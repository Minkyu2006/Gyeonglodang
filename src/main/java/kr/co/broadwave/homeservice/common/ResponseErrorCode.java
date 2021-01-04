package kr.co.broadwave.homeservice.common;

/**
 * @author InSeok
 * Date : 2019-04-04
 * Time : 09:39
 * Remark : Rest controller 응답코드
 */
public enum ResponseErrorCode {
    E001("E001", "아이디와 이름을 Json 형식에 맞추어 넣어주세요"),

    ;

    private String code;
    private String desc;

    ResponseErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
