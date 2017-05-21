// CArtAgO artifact code for project tp_agentes

package tp_agentes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		defineObsProperty("negotiate", false);
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
		boolean readyToNegotiate = consumptionConsumer1.size() == 7 && consumptionConsumer2.size() == 7 
				&& consumptionConsumer3.size() == 7 && consumptionConsumer4.size() == 7  ;
		System.out.println("Ready to negociate: " + readyToNegotiate);
		getObsProperty("negotiate").updateValue(readyToNegotiate);
	}
	
	@OPERATION
	public void doNegotiation() {
		String [] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for(String day : days){
			List<Consumption> consumptions = new ArrayList<Consumption> ();
			consumptions.add(new Consumption (consumptionConsumer1.get(day), CONSUMER_1, day)); 
			consumptions.add(new Consumption (consumptionConsumer2.get(day), CONSUMER_2, day));
			consumptions.add(new Consumption (consumptionConsumer3.get(day), CONSUMER_3, day)); 
			consumptions.add(new Consumption (consumptionConsumer4.get(day), CONSUMER_4, day)); 
			checkZeros(consumptions);
			exchangeEnergy(consumptions);
		}
	}
	
	private void checkZeros (List <Consumption> consumptions){
		List <Consumption> noDebitConsumptions = new ArrayList<Consumption> (consumptions.size());
		for(Consumption consumption : consumptions){
			if(consumption.getValue() == 0){
				noDebitConsumptions.add(consumption);
				defineObsProperty("printNoDebitConsumer", consumption.getDay(), consumption.getConsumer(), consumption.getValue());
			}
		}
		
		for(Consumption noDebitConsumption : noDebitConsumptions){
			consumptions.remove(noDebitConsumption);
		}
	}
	
	private void exchangeEnergy(List <Consumption> consumptions){
		Collections.sort(consumptions, new Comparator<Consumption>() {
	        public int compare(Consumption c1, Consumption c2) {
	        	if(c1.getValue() < 0 && c2.getValue() < 0){
	        		if(c1.getValue() > c2.getValue()){
						return -1;
					}
					else if(c1.getValue() < c2.getValue()){
						return 1;
					}
	        	} else {
	        		if(c1.getValue() > c2.getValue()){
						return 1;
					}
					else if(c1.getValue() < c2.getValue()){
						return -1;
					}
	        	}
				
				return 0;
	        }
	       });
		
		//If the first is upper than zero, so negotiation isn't be necessary this day.
		if(consumptions.get(0).getValue() > 0){
			for(Consumption consumption : consumptions){
				defineObsProperty("printExcessEnergyConsumer", consumption.getDay(), consumption.getConsumer(), consumption.getValue());
			}
		}
		else {
			for(int i = 0, y= consumptions.size() -1; i < consumptions.size() && y >=0 && i <y; i++){
				while (consumptions.get(i).getValue() < 0 && i != y ) {
					double leftValue = consumptions.get(i).getValue();
					double rightValue = consumptions.get(y).getValue();
					if(leftValue + rightValue < 0){
						consumptions.get(i).setValue(leftValue + rightValue);
						consumptions.get(y).setValue(0);
						defineObsProperty("printLoanEnergyBorrower", consumptions.get(i).getDay(), consumptions.get(i).getConsumer(), consumptions.get(y).getConsumer(), rightValue);
						defineObsProperty("printLoanEnergyLender", consumptions.get(i).getDay(), consumptions.get(i).getConsumer(), consumptions.get(y).getConsumer(), rightValue);
						y--;
					}
					else{
						consumptions.get(i).setValue(0);
						consumptions.get(y).setValue(rightValue +leftValue);
						defineObsProperty("printLoanEnergyBorrower", consumptions.get(i).getDay(), consumptions.get(i).getConsumer(), consumptions.get(y).getConsumer(), -leftValue);
						defineObsProperty("printLoanEnergyLender", consumptions.get(i).getDay(), consumptions.get(i).getConsumer(), consumptions.get(y).getConsumer(), -leftValue);
						if(consumptions.get(y).getValue() == 0){
							y--;
						}
					}
				}
				if(i == y && consumptions.get(i).getValue() < 0){
					defineObsProperty("printNoSufficientEnergy", consumptions.get(i).getDay(), consumptions.get(i).getConsumer(), -consumptions.get(i).getValue());
				}
			}
		}
		
		double excessEnergy = 0;
		for(Consumption consumption : consumptions){
				excessEnergy += consumption.getValue();
		}
		
		if(excessEnergy > 0){
			defineObsProperty("printExcessEnergySupervisor", consumptions.get(0).getDay(), excessEnergy);
		}
	}
	
	
	private class Consumption {
		double value;
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
		
		public void setValue(double value) {
			this.value = value;
		}
		
		
		public String getDay() {
			return day;
		}

		public String getConsumer() {
			return consumer;
		}

		@Override
		public String toString() {
			return "Consumption [value=" + value + ", consumer=" + consumer + ", day=" + day + "]";
		}
		
	}
}

