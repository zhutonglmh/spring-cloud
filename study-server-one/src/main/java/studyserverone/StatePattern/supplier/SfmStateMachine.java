package studyserverone.StatePattern.supplier;

import studyserverone.StatePattern.supplier.constant.SfmEventTypeEnum;
import studyserverone.StatePattern.supplier.state.impl.SfmState;
import studyserverone.StatePattern.supplier.state.impl.SfmStateFactory;

public class SfmStateMachine {
	public static final void init() {
		// 绑定审核中状态下的允许操作的事件
		SfmState auditingState = SfmStateFactory.getAuditingState();
		auditingState.registerEventType(SfmEventTypeEnum.AUDIT);
		auditingState.registerEventType(SfmEventTypeEnum.REJECT);

		// TODO 绑定其他
		SfmState rejectdState = SfmStateFactory.getRejectedState();
		rejectdState.registerEventType(SfmEventTypeEnum.AUDIT);
		rejectdState.registerEventType(SfmEventTypeEnum.REJECT);

	}
}
