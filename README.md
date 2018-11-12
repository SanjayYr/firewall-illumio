# firewall-illumio
Illumio coding assessment

1) Design and implementation details with asssumptions
  - Firewall.java (src/main/java/Firewall.java) has all the implementation code
  - FirewallTest.java (src/test/java/FirewallTest.java) has the test code
  - Firewall.java has an inner class 'Rule' which defines the structure of the rules in the input file
  - Implementation logic reads all the rule entries in the csv file, create a 'Rule' object and add the object
    to a list of rules.
  - The 'direction' and 'protocol' fields in Rule class are of type char (first character of the strings) to save space      
    and also time complexity during comparison.
    'i' for inbound and 'o' for 'outbound' in direction. 't' for tcp and 'u' for udp in protocol
  - The 'ip_address' field is stored as a long (converted from String format) to save space and time complexity during 
    comparison. 'ip_address' is of type long[] and 'port' is of type int[] to take care of range.
  - port[0] and port[1] are the start and end of the port number range. ip[0] and ip[1] are the start and end of 
    ip_address range.


2) How to Run?
  - The code is developed as a Maven project
  - Commands to run:
    mvn clean;        //  From the project root directory
    
    mvn package -Dtest=FirewallTest -DfilePath='absoulte-path-of-the-file'
    For ex:
    mvn package -Dtest=FirewallTest -DfilePath=/home/hadoop/illumio/firewall-illumio/test.csv

3) Testing


4) Performance Considerations.


5) Teams interested in.

  Preference 1: Data team,
  Preference 2: Platform team
