// Agent supervisor in project tp_agentes

/* Initial beliefs and rules */

/* Initial goals */

!startSupervisor("SupervisorId").

/* Plans */

+!startSupervisor(Id) <- 
		.print("Starting Supervisor ");
		.my_name(Name);
		initializeSupervisorArtifact(Name);
		.wait(2000);
		!requestConsumption.
		
+!requestConsumption <- .print("Requesting consuption");
		.broadcast(achieve, sendConsumption).

+consumptions(Day, Consumption, Consumer) <- .print ("Received ", Day, " Consumptions: ", Consumption, " from: ", Consumer);
										receiveConsumptions(Day, Consumption, Consumer).

+negociate(Ready) : Ready == true <-  
		.print("I'm ready to negociate!!");
		doNegociation.
+printNoDebitConsumer(Day, Consumer, Value) <- 
					.print("On ", Day, ", ", Consumer, " wastes all the available energy for him.").
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation  
//{ include("$jacamoJar/templates/org-obedient.asl") }
