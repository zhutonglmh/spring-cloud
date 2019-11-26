package studyserverone.StatePattern.supplier.constant;

public enum SfmEventTypeEnum {
	//
	TEMP_STORAGE(1, "ÔÝ´æ"),
	//
	SUBMIT(2, "Ìá½»"),
	//
	AUDIT(3, "ÉóºË"),
	//
	WITHDRAW(4, "³·»Ø"),
	//
	REJECT(5, "²µ»Ø"),
	//
	UN_AUDIT(6, "·´ÉóºË"),
	//
	CANCEL(7, "×÷·Ï"),
	//
	DELETE(8, "É¾³ý");

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
