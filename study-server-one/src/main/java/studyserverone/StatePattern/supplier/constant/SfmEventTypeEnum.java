package studyserverone.StatePattern.supplier.constant;

public enum SfmEventTypeEnum {
	//
	TEMP_STORAGE(1, "�ݴ�"),
	//
	SUBMIT(2, "�ύ"),
	//
	AUDIT(3, "���"),
	//
	WITHDRAW(4, "����"),
	//
	REJECT(5, "����"),
	//
	UN_AUDIT(6, "�����"),
	//
	CANCEL(7, "����"),
	//
	DELETE(8, "ɾ��");

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
