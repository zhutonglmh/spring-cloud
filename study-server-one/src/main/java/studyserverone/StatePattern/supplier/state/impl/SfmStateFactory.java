package studyserverone.StatePattern.supplier.state.impl;

import studyserverone.StatePattern.supplier.constant.SfmBillStateTypeEnum;

public class SfmStateFactory {
	private static final SfmState IS_AUDITING_STATE = new SfmState(SfmBillStateTypeEnum.IS_AUDITING);
	private static final SfmState REJECTED_STATE = new SfmState(SfmBillStateTypeEnum.REJECTED);
	
	public static final SfmState getAuditingState() {
		return IS_AUDITING_STATE;
	}
	
	public static final SfmState getRejectedState() {
		return REJECTED_STATE;
	}
	
	public static final SfmState getStateOf(int stateCode) {
		SfmBillStateTypeEnum stateType = SfmBillStateTypeEnum.valueOf(stateCode);
		if (stateType != null) {
			return new SfmState(stateType);
		}
		
		return null;
	}
	
}
