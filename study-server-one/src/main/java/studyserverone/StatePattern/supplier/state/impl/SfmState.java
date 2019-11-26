package studyserverone.StatePattern.supplier.state.impl;

import studyserverone.StatePattern.supplier.constant.SfmBillStateTypeEnum;
import studyserverone.StatePattern.supplier.constant.SfmEventTypeEnum;
import studyserverone.StatePattern.supplier.event.SfmEventBase;
import studyserverone.StatePattern.supplier.state.SfmStateInterface;

import java.util.ArrayList;
import java.util.List;

public class SfmState implements SfmStateInterface {
	protected final SfmBillStateTypeEnum stateType;

	protected SfmState(SfmBillStateTypeEnum stateType) {
		this.stateType = stateType;
	}

	// 允许注册到此状态的事件List
	private List<SfmEventTypeEnum> permitEventTypes = new ArrayList<>();

	@Override
	public final <T> void execute(SfmEventBase<T> event) {
		if (event == null) {
			throw new IllegalArgumentException("SfmStateBase#registerEvent(SfmEventEnum) ：参数为空。");
		}

		if (!isRegistedEvent(event.getEventType())) {
			throw new IllegalArgumentException("SfmStateBase#execute(SfmEventEnum) : 该事件【"
					+ event.getEventType().getName() + "】没有注册到本状态 【 " + this.getStateType().getName() + "】中。");
		}

		this.executeEvent(event);
	}

	protected <T> void executeEvent(SfmEventBase<T> event) {
		event.setPremiseState(this);
		event.doAction();
	}

	public boolean isRegistedEvent(SfmEventTypeEnum eventType) {
		return permitEventTypes.contains(eventType);
	}

	public void registerEventType(SfmEventTypeEnum eventType) {
		if (eventType == null) {
			throw new IllegalArgumentException("SfmStateBase#registerEvent(SfmEventEnum) ：参数为空。");
		}

		if (isRegistedEvent(eventType)) {
			throw new IllegalArgumentException("SfmStateBase#registerEvent(SfmEventEnum) ：该事件已经加入过了。");
		}

		this.permitEventTypes.add(eventType);
	}

	public void registerEventTypes(SfmEventTypeEnum... eventTypes) {
		if (eventTypes == null || eventTypes.length == 0) {
			throw new IllegalArgumentException("SfmStateBase#registerEvents(SfmEventEnum) ：参数为空。");
		}

		for (SfmEventTypeEnum event : eventTypes) {
			this.permitEventTypes.add(event);
		}
	}

	public void removeEventType(SfmEventTypeEnum eventTypes) {
		if (eventTypes == null) {
			throw new IllegalArgumentException("SfmStateBase#removeEvent(SfmEventEnum) ：参数为空。");
		}

		this.permitEventTypes.remove(eventTypes);
	}

	public SfmBillStateTypeEnum getStateType() {
		return stateType;
	}

}
