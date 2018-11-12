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
  - If the number of rules in the file is very high, we can make use of concurrent processing of the rule list to speed 
    up the process. 
  - In Java, we can use Executor service to create a fixed number of threads according to the application requirements 
    and then let all the threads check a part of the rule list to see if any rule matches the given input. 
  - For concurrent read access by multiple threads, we can use 'CopyOnWriteArrayList' in Java. CopyOnWriteArrayList lets
    multiple threads do a read access to the list at the same time without any locking since threads are not modifying  
    the list. Then, when any of the threads finds a matching rule, all the threads stop processing the list and return 
    true.
  - There are overheads of creating and destroying of threads and also using CopyOnWriteArrayList here. Hence, this 
    method should be used only when input size is very large and rule checking by single thread is not quick enough.

6) Teams interested in.
  - I am concentrating on Distributed Systems, Big Data and Cloud Computing in my masters. I have also done several 
    projects and internships in the above domain which involved working with NoSQL databases, Hadoop ecosystem and Big 
    Data software development. I have experience with tools and technologies such as Hadoop, Spark, Kafka, Cassandra and 
    Elasticsearch. I have software development experience in Java domain. I also have some experience with Python and 
    Scala languages. I would like to work in a team that involves working in the above domains or related field. 

    Preference 1: Data team,
    Preference 2: Platform team
