// Agent consumer in project tp_agentes

/* Initial beliefs and rules */

/* Initial goals */


/* Plans */

+!startConsumer(SupervisorId) <- 
	        lookupArtifact(SupervisorId, ArtifactId);
	        .my_name(Name);
	        .print("I am ", Name);
			focus(ArtifactId);
			initialize(Name).
			
+!sendDayConsumption(Day)<-
			.print("Sending consumption for Day ", Day);
			receiveConsumption(1.0, "pepe").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation  
//{ include("$jacamoJar/templates/org-obedient.asl") }
