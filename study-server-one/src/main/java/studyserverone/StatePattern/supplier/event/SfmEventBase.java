package studyserverone.StatePattern.supplier.event;


import studyserverone.StatePattern.supplier.constant.SfmEventTypeEnum;
import studyserverone.StatePattern.supplier.state.impl.SfmState;

public abstract class SfmEventBase<T> implements SfmEventInterface {
	protected T t;
	protected final SfmEventTypeEnum eventType;
	// ��ʲô״̬��ִ�д˶����¼�
	protected SfmState premiseState;
	
	protected SfmEventBase(SfmEventTypeEnum eventType, T t) {
		super();
		this.t = t;
		this.eventType = eventType;
	}
	
	public SfmEventTypeEnum getEventType() {
		return eventType;
	}

	public SfmState getPremiseState() {
		return premiseState;
	}

	public void setPremiseState(SfmState premiseState) {
		this.premiseState = premiseState;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(512);
		sb.append("��״̬��");
		sb.append(this.premiseState.getStateType().getName());
		sb.append(" �£�ִ�У�");
		sb.append(this.eventType.getName());
		sb.append(" ������");
		
		return sb.toString();
	}
	
}
