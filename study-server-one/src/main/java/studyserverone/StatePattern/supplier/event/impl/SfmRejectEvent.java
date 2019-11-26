package studyserverone.StatePattern.supplier.event.impl;

import studyserverone.StatePattern.supplier.constant.SfmEventTypeEnum;
import studyserverone.StatePattern.supplier.event.SfmEventBase;
import studyserverone.StatePattern.supplier.util.PrintLogUtil;

public class SfmRejectEvent<T> extends SfmEventBase<T> {
	protected SfmRejectEvent(SfmEventTypeEnum eventType, T t) {
		super(eventType, t);
	}

	@Override
	public void doAction() {
		PrintLogUtil.println(this.toString());
		// TODO
		
	}
	
}
