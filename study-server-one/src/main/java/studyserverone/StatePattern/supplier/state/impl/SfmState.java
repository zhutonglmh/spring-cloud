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

	// ����ע�ᵽ��״̬���¼�List
	private List<SfmEventTypeEnum> permitEventTypes = new ArrayList<>();

	@Override
	public final <T> void execute(SfmEventBase<T> event) {
		if (event == null) {
			throw new IllegalArgumentException("SfmStateBase#registerEvent(SfmEventEnum) ������Ϊ�ա�");
		}

		if (!isRegistedEvent(event.getEventType())) {
			throw new IllegalArgumentException("SfmStateBase#execute(SfmEventEnum) : ���¼���"
					+ event.getEventType().getName() + "��û��ע�ᵽ��״̬ �� " + this.getStateType().getName() + "���С�");
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
			throw new IllegalArgumentException("SfmStateBase#registerEvent(SfmEventEnum) ������Ϊ�ա�");
		}

		if (isRegistedEvent(eventType)) {
			throw new IllegalArgumentException("SfmStateBase#registerEvent(SfmEventEnum) �����¼��Ѿ�������ˡ�");
		}

		this.permitEventTypes.add(eventType);
	}

	public void registerEventTypes(SfmEventTypeEnum... eventTypes) {
		if (eventTypes == null || eventTypes.length == 0) {
			throw new IllegalArgumentException("SfmStateBase#registerEvents(SfmEventEnum) ������Ϊ�ա�");
		}

		for (SfmEventTypeEnum event : eventTypes) {
			this.permitEventTypes.add(event);
		}
	}

	public void removeEventType(SfmEventTypeEnum eventTypes) {
		if (eventTypes == null) {
			throw new IllegalArgumentException("SfmStateBase#removeEvent(SfmEventEnum) ������Ϊ�ա�");
		}

		this.permitEventTypes.remove(eventTypes);
	}

	public SfmBillStateTypeEnum getStateType() {
		return stateType;
	}

}
