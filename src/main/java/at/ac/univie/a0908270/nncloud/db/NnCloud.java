package at.ac.univie.a0908270.nncloud.db;

import at.ac.univie.a0908270.nncloud.util.NnStatus;

public class NnCloud {
	protected NnStatus status;
	
	protected String dl4jNetwork;
	
	public NnCloud() {
		this.status = NnStatus.CREATED;
	}
	
	public NnStatus getStatus() {
		return status;
	}
	
	public void setStatus(NnStatus status) {
		this.status = status;
	}
	
	public String getDl4jNetwork() {
		return dl4jNetwork;
	}
	
	public void setDl4jNetwork(String dl4jNetwork) {
		this.dl4jNetwork = dl4jNetwork;
	}
	
}
