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
  - I have used JUnit testing framework for testing.
  - I ran the test cases for most of the corner cases. I have included the test case with input file and queries given in 
    the csv file. 
  - More test cases can be easily added to FirewallTest.java with very little modifications to the code
  - I also tested for the case where input file has 1M entries and observed that the query run was reasonably quick.


4) Performance Considerations.
  - The 'Rule' object is optimized to store as less information as possible for the rule entries.
  - The 'direction' and 'protocol' are stored using a single character to optimize space and time. Since the possible 
    values of 'direction' and 'protocol' are just 2, I could have gone with boolean data type. But, boolean data type 
    takes more (how much more is implementation dependent) than 1 byte in Java. Hence, storing as a char is efficient.
  - Each 'Rule' object is of size 1 + 1 + 2*4 + 2*8 = 26 bytes. For 1M entries, the total size of the ruleList is 26MB 
    which is of reasonable size and quick to respond while running queries.
    

5) Further refinements and Optimizations.
  - 

6) Teams interested in.

  Preference 1: Data team,
  Preference 2: Platform team
