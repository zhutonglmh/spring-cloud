package studyserverone.StatePattern.supplier.state;

import studyserverone.StatePattern.supplier.event.SfmEventBase;

public interface SfmStateInterface {
	public <T> void execute(SfmEventBase<T> event);
}
