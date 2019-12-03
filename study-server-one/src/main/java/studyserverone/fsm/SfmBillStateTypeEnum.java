package studyserverone.fsm;

/**
 * @author zhutong
 * @date 2019/12/2 14:28
 */
public enum SfmBillStateTypeEnum {
    
    //
    TEMP_STORAGE(1, "暂存"),
    //
    IS_AUDITING(2, "审核中"),
    //
    AUDITED(3, "已审核"),
    //
    REJECTED(5, "已驳回"),
    //
    UN_ANDITED(6, "已反审"),
    //
    CACALED(7, "已作废");
    
    private int code;
    private String name;
    
    private SfmBillStateTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public static final studyserverone.StatePattern.supplier.constant.SfmBillStateTypeEnum valueOf(int code) {
        for (studyserverone.StatePattern.supplier.constant.SfmBillStateTypeEnum s : studyserverone.StatePattern.supplier.constant.SfmBillStateTypeEnum.values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        
        return null;
    }
}
