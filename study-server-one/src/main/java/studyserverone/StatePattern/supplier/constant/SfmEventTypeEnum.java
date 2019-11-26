package studyserverone.StatePattern.supplier.constant;

public enum SfmEventTypeEnum {
	//
	TEMP_STORAGE(1, "暂存"),
	//
	SUBMIT(2, "提交"),
	//
	AUDIT(3, "审核"),
	//
	WITHDRAW(4, "撤回"),
	//
	REJECT(5, "驳回"),
	//
	UN_AUDIT(6, "反审核"),
	//
	CANCEL(7, "作废"),
	//
	DELETE(8, "删除");

	private int code;
	private String name;

	private SfmEventTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
