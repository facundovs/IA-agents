// CArtAgO artifact code for project tp_agentes

package tp_agentes;

import cartago.*;

public class SupervisorArtifact extends Artifact {
	void init(int initialValue) {
		defineObsProperty("count", initialValue);
	}
	
	@OPERATION
	public void initialize(String name) {
		System.out.println("Artifact was focused by " + name);
	}
	
	@OPERATION
	public void receiveConsumption(double consumption, String name) {
		System.out.println("Consumption received: " + consumption + " from " + name);
	}
}

