// Agent consumer in project tp_agentes

/* Initial beliefs and rules */

/* Initial goals */

!startConsumer.

/* Plans */

+!startConsumer <- 
	        .my_name(Name);
	        .print("I am ", Name);
			initializeSupervisorArtifact(Name).
						
+!sendConsumption <-
			.print("Sending Consumption");
			.my_name(Name);
			setConsumptions(Name). 


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation  
//{ include("$jacamoJar/templates/org-obedient.asl") }
