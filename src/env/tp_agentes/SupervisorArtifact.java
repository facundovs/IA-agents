// CArtAgO artifact code for project tp_agentes

package tp_agentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cartago.*;

public class SupervisorArtifact extends Artifact {
	
	public static final String CONSUMER_1 = "consumer1";
	public static final String CONSUMER_2 = "consumer2";
	public static final String CONSUMER_3 = "consumer3";
	public static final String CONSUMER_4 = "consumer4";

	
	Map<String, Double> consumptionConsumer1 = new HashMap <String, Double> ();
	Map<String, Double> consumptionConsumer2 = new HashMap <String, Double> ();
	Map<String, Double> consumptionConsumer3 = new HashMap <String, Double> ();
	Map<String, Double> consumptionConsumer4 = new HashMap <String, Double> ();

	
	void init(int initialValue) {
	}
	
	@OPERATION
	public void initializeSupervisorArtifact(String name) {
		defineObsProperty("negociate", false);
		System.out.println("Artifact was focused by " + name);
	}
	
	@OPERATION
	public void receiveConsumptions(String day, double consumption, String consumer) {
		System.out.println("Consumptions received: "+ ", Day: " +  day +  ", Consumption: " + consumption + ", Consumer: " +  consumer);
		if(CONSUMER_1.equals(consumer)){
			consumptionConsumer1.put(day, consumption);
		} else if (CONSUMER_2.equals(consumer)){
			consumptionConsumer2.put(day, consumption);
		}else if (CONSUMER_3.equals(consumer)){
			consumptionConsumer3.put(day, consumption);
		}else if (CONSUMER_4.equals(consumer)){
			consumptionConsumer4.put(day, consumption);
		}
		boolean readyToNegociate = consumptionConsumer1.size() == 7 && consumptionConsumer2.size() == 7 
				&& consumptionConsumer3.size() == 7 && consumptionConsumer4.size() == 7  ;
		System.out.println("Ready to negociate: " + readyToNegociate);
		getObsProperty("negociate").updateValue(readyToNegociate);
	}
	
	@OPERATION
	public void doNegociation() {
		String [] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for(String day : days){
			List<Consumption> consumptions = new ArrayList<Consumption> ();
			consumptions.add(new Consumption (consumptionConsumer1.get(day), CONSUMER_1, day)); 
			consumptions.add(new Consumption (consumptionConsumer2.get(day), CONSUMER_2, day));
			consumptions.add(new Consumption (consumptionConsumer3.get(day), CONSUMER_3, day)); 
			consumptions.add(new Consumption (consumptionConsumer4.get(day), CONSUMER_4, day)); 
			checkZeros(consumptions);
		}
	}
	
	private void checkZeros (List <Consumption> consumptions){
		for(Consumption consumption : consumptions){
			if(consumption.getValue() == 0){
				//consumptions.remove(consumption);
				defineObsProperty("printNoDebitConsumer", consumption.getDay(), consumption.getConsumer(), consumption.getValue());
			}
		}
	}
	
	
	private class Consumption {
		final double value;
		final String consumer;
		final String day;
		
		public Consumption(double value, String consumer, String day){
			this.value = value;
			this.consumer = consumer;
			this.day = day;
		}

		public double getValue() {
			return value;
		}
		
		public String getDay() {
			return day;
		}

		public String getConsumer() {
			return consumer;
		}
		
	}
}

