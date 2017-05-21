// Agent consumer in project tp_agentes

/* Initial beliefs and rules */

/* Initial goals */

!startConsumer.

/* Plans */

+!startConsumer <- 
	  .wait(1000);
      .my_name(Name); 
      commitMission(mConsumer)[artifact_id(consumptionSchemeInstance)].   
						
+!sendConsumptions<-
			.print("Sending Consumption");
			.my_name(Name);
			setConsumptions(Name). 


+printNoDebitConsumer(Day, Consumer, Value) <-
					.my_name(Name);
					if(.substring(Name, Consumer)){     //we don't know how to compare Strings ( == and == operators results in false).
						.print("On ", Day, ", I consumed all the available energy for my house.");
					}.
					
+printExcessEnergyConsumer(Day, Consumer, Value) <-
					.my_name(Name);
					if(.substring(Name, Consumer)){     //we don't know how to compare Strings ( == and == operators results in false).
						.print("On ", Day, ", I had excess of energy. However nobody needs it.");
					}.

+printLoanEnergyBorrower(Day, Consumer, LoanConsumer, Value) <-
					.my_name(Name);
					if(.substring(Name, Consumer)){     //we don't know how to compare Strings ( == and == operators results in false).
						.print("On ", Day, ", ", LoanConsumer, " loaned me ", Value, "Kw/h of energy.");
					}.
					
+printLoanEnergyLender(Day, Consumer, LoanConsumer, Value) <-
				.my_name(Name);
				if(.substring(Name, LoanConsumer)){     //we don't know how to compare Strings ( == and == operators results in false).
					.print("On ", Day, ", ", "I loaned ", Value, "Kw/h of energy to ", Consumer);
				}.

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation  
{ include("$jacamoJar/templates/org-obedient.asl") }
