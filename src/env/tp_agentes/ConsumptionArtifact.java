// CArtAgO artifact code for project tp_agentes

package tp_agentes;

import cartago.*;

public class ConsumptionArtifact extends Artifact {
	
	public static final String CONSUMER_1 = "consumer1";
	public static final String CONSUMER_2 = "consumer2";
	public static final String CONSUMER_3 = "consumer3";
	public static final String CONSUMER_4 = "consumer4";

	//The followings vectors are the averages of the data sets.
	private int [] consumptionsConsumer1 = {-1, 0, 0, 1, 3, 2, -5};
	private int [] consumptionsConsumer2 = {0, 3, -1, 4, 3, -2, 4};
	private int [] consumptionsConsumer3 = {0, 2, 2, 0, -1, 0, 0};
	private int [] consumptionsConsumer4 = {1, 5, 6, 6, 1, 0, -2};

	void init(int initialValue) {
	}
	
	@OPERATION
	public void setConsumptions(String nameOfConsumer) {
		if (CONSUMER_1.equals(nameOfConsumer)){
			defineObsProperty("consumptions", "Monday", consumptionsConsumer1[0], CONSUMER_1);
			defineObsProperty("consumptions", "Tuesday", consumptionsConsumer1[1], CONSUMER_1);
			defineObsProperty("consumptions", "Wednesday", consumptionsConsumer1[2], CONSUMER_1);
			defineObsProperty("consumptions", "Thursday", consumptionsConsumer1[3], CONSUMER_1);
			defineObsProperty("consumptions", "Friday", consumptionsConsumer1[4], CONSUMER_1);
			defineObsProperty("consumptions", "Saturday", consumptionsConsumer1[5], CONSUMER_1);
			defineObsProperty("consumptions", "Sunday", consumptionsConsumer1[6], CONSUMER_1);
			System.out.println("Consumer 1 properties were initialized");
		} else if (CONSUMER_2.equals(nameOfConsumer)){
			defineObsProperty("consumptions", "Monday", consumptionsConsumer2[0], CONSUMER_2);
			defineObsProperty("consumptions", "Tuesday", consumptionsConsumer2[1], CONSUMER_2);
			defineObsProperty("consumptions", "Wednesday", consumptionsConsumer2[2], CONSUMER_2);
			defineObsProperty("consumptions", "Thursday", consumptionsConsumer2[3], CONSUMER_2);
			defineObsProperty("consumptions", "Friday", consumptionsConsumer2[4], CONSUMER_2);
			defineObsProperty("consumptions", "Saturday", consumptionsConsumer2[5], CONSUMER_2);
			defineObsProperty("consumptions", "Sunday", consumptionsConsumer2[6], CONSUMER_2);
			System.out.println("Consumer 2 properties were initialized");
		} else if (CONSUMER_3.equals(nameOfConsumer)){
			defineObsProperty("consumptions", "Monday", consumptionsConsumer3[0], CONSUMER_3);
			defineObsProperty("consumptions", "Tuesday", consumptionsConsumer3[1], CONSUMER_3);
			defineObsProperty("consumptions", "Wednesday", consumptionsConsumer3[2], CONSUMER_3);
			defineObsProperty("consumptions", "Thursday", consumptionsConsumer3[3], CONSUMER_3);
			defineObsProperty("consumptions", "Friday", consumptionsConsumer3[4], CONSUMER_3);
			defineObsProperty("consumptions", "Saturday", consumptionsConsumer3[5], CONSUMER_3);
			defineObsProperty("consumptions", "Sunday", consumptionsConsumer3[6], CONSUMER_3);
			System.out.println("Consumer 3 properties were initialized");
		} else if (CONSUMER_4.equals(nameOfConsumer)){
			defineObsProperty("consumptions", "Monday", consumptionsConsumer4[0], CONSUMER_4);
			defineObsProperty("consumptions", "Tuesday", consumptionsConsumer4[1], CONSUMER_4);
			defineObsProperty("consumptions", "Wednesday", consumptionsConsumer4[2], CONSUMER_4);
			defineObsProperty("consumptions", "Thursday", consumptionsConsumer4[3], CONSUMER_4);
			defineObsProperty("consumptions", "Friday", consumptionsConsumer4[4], CONSUMER_4);
			defineObsProperty("consumptions", "Saturday", consumptionsConsumer4[5], CONSUMER_4);
			defineObsProperty("consumptions", "Sunday", consumptionsConsumer4[6], CONSUMER_4);
			System.out.println("Consumer 4 properties were initialized");
		}
	}
}

