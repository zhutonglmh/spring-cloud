package studyserverone.StatePattern.supplier.constant;

public enum SfmBillStateTypeEnum {
	//
	TEMP_STORAGE(1, "�ݴ�"),
	//
	IS_AUDITING(2, "�����"),
	//
	AUDITED(3, "�����"),
	//
	REJECTED(5, "�Ѳ���"),
	//
	UN_ANDITED(6, "�ѷ���"),
	//
	CACALED(7, "������");

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
