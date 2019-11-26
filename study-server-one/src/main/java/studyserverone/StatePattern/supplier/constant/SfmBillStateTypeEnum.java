package studyserverone.StatePattern.supplier.constant;

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
	
	public static final SfmBillStateTypeEnum valueOf(int code) {
		for (SfmBillStateTypeEnum s : SfmBillStateTypeEnum.values()) {
			if (s.getCode() == code) {
				return s;
			}
		}
		
		return null;
	}
	
	

}
