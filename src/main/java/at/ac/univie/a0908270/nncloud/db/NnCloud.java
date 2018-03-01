package at.ac.univie.a0908270.nncloud.db;

import at.ac.univie.a0908270.nncloud.util.NnStatus;

public class NnCloud {
	protected NnStatus status;
	
	public NnCloud() {
		this.status = NnStatus.CREATED;
	}
	
	public NnStatus getStatus() {
		return status;
	}
	
	public void setStatus(NnStatus status) {
		this.status = status;
	}
}
