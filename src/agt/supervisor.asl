// Agent supervisor in project tp_agentes

/* Initial beliefs and rules */

/* Initial goals */

!startSupervisor("SupervisorId").

/* Plans */

+!startSupervisor(Id) <- makeArtifact(Id, "tp_agentes.SupervisorArtifact",[], ArtifactId);
		.print("Starting Supervisor ");
		.my_name(Name);
		.print("Artifact created for ", Name);
		focus(ArtifactId);
		.print("ArtifactId: ", ArtifactId);
		initialize(Name);
		.broadcast(achieve, startConsumer(Id));
		.wait(2000);
		!requestConsumption.
		
+!requestConsumption <- .print("Requesting consuption");
		.broadcast(achieve, sendDayConsumption("Monday")).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation  
//{ include("$jacamoJar/templates/org-obedient.asl") }
