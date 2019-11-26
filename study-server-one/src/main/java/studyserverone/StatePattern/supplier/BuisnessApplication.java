package studyserverone.StatePattern.supplier;

import studyserverone.StatePattern.supplier.event.impl.SfmEventFactory;
import studyserverone.StatePattern.supplier.state.impl.SfmState;
import studyserverone.StatePattern.supplier.state.impl.SfmStateFactory;

import java.util.HashMap;
import java.util.Map;

public class BuisnessApplication {
	public static void main(String[] args) {
		SfmStateMachine.init();

		Map<String, String> execParam = new HashMap<>();

		// ģ����֪״̬������µ��ö���
		SfmState auditingState = SfmStateFactory.getAuditingState();
		auditingState.execute(SfmEventFactory.getAuditEvent(execParam));
		auditingState.execute(SfmEventFactory.getRejectEvent(execParam));

		// ģ����֪״̬������µ��ö���
		SfmState rejectedState = SfmStateFactory.getRejectedState();
		rejectedState.execute(SfmEventFactory.getAuditEvent(execParam));
		rejectedState.execute(SfmEventFactory.getRejectEvent(execParam));

		// ģ������ݿ�ȡ��״̬�������µ��ö���
		SfmState stateOfDB = SfmStateFactory.getStateOf(3);
		stateOfDB.execute(SfmEventFactory.getAuditEvent(execParam));
	}
}
