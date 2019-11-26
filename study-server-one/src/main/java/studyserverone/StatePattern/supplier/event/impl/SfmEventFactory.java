package studyserverone.StatePattern.supplier.event.impl;

import studyserverone.StatePattern.supplier.constant.SfmEventTypeEnum;
import studyserverone.StatePattern.supplier.event.SfmEventBase;

public class SfmEventFactory {
	public static final <T> SfmEventBase<T> getAuditEvent(T t) {
		return new SfmAuditEvent<T>(SfmEventTypeEnum.AUDIT, t);
	}

	public static final <T> SfmEventBase<T> getRejectEvent(T t) {
		return new SfmAuditEvent<T>(SfmEventTypeEnum.REJECT, t);
	}

}
