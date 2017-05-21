// Agent supervisor in project tp_agentes

/* Initial beliefs and rules */

/* Initial goals */

!startNegotiation.

/* Plans */

+!startNegotiation <- 
	  .wait(1000);
      .my_name(Name); 
      setOwner(Name)[artifact_id(consumptionSchemeInstance)];  
      commitMission(mSupervisor)[artifact_id(consumptionSchemeInstance)].      


+!start <- 
		.print("Starting Supervisor ");
		.my_name(Name);
		initializeSupervisorArtifact(Name);
		.wait(2000).
				
+!requestConsumption <- .print("Requesting consuption").

+consumptions(Day, Consumption, Consumer) <- .print ("Received ", Day, " Consumptions: ", Consumption, " from: ", Consumer);
										receiveConsumptions(Day, Consumption, Consumer).

+!doEnergyExchange: negotiate(true) <- .print("Doing exchange of energy");
					doNegotiation.


		

+printNoSufficientEnergy(Day, Consumer, Value) <- .print("On ", Day, ", No enough energy for ", Consumer, ". He still needed ", Value, "Kw/H. We needed energy from other city").

+printExcessEnergySupervisor(Day, Value) <- .print("On ", Day, ", We could provide ", Value, "Kw/H of energy to another city.").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation  
{ include("$jacamoJar/templates/org-obedient.asl") }
