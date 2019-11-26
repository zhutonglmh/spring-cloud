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

		// 模拟已知状态的情况下调用动作
		SfmState auditingState = SfmStateFactory.getAuditingState();
		auditingState.execute(SfmEventFactory.getAuditEvent(execParam));
		auditingState.execute(SfmEventFactory.getRejectEvent(execParam));

		// 模拟已知状态的情况下调用动作
		SfmState rejectedState = SfmStateFactory.getRejectedState();
		rejectedState.execute(SfmEventFactory.getAuditEvent(execParam));
		rejectedState.execute(SfmEventFactory.getRejectEvent(execParam));

		// 模拟从数据库取得状态后的情况下调用动作
		SfmState stateOfDB = SfmStateFactory.getStateOf(3);
		stateOfDB.execute(SfmEventFactory.getAuditEvent(execParam));
	}
}
