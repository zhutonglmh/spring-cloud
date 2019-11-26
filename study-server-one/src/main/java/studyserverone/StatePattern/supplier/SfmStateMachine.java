package studyserverone.StatePattern.supplier;

import studyserverone.StatePattern.supplier.constant.SfmEventTypeEnum;
import studyserverone.StatePattern.supplier.state.impl.SfmState;
import studyserverone.StatePattern.supplier.state.impl.SfmStateFactory;

public class SfmStateMachine {
	public static final void init() {
		// �������״̬�µ�����������¼�
		SfmState auditingState = SfmStateFactory.getAuditingState();
		auditingState.registerEventType(SfmEventTypeEnum.AUDIT);
		auditingState.registerEventType(SfmEventTypeEnum.REJECT);

		// TODO ������
		SfmState rejectdState = SfmStateFactory.getRejectedState();
		rejectdState.registerEventType(SfmEventTypeEnum.AUDIT);
		rejectdState.registerEventType(SfmEventTypeEnum.REJECT);

	}
}
