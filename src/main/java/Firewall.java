package com.illumio.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Firewall {

    private static class Rule{
        // Storing direction and protocol as char (first char of direction String)
        // for each field. It saves space and also time during comparison.
        // Not storing it as boolean since boolean in Java may take more than 1 byte
        // 'i': inbound, 'o': outbound
        char direction;
        // 't': tcp, 'u': udp
        char protocol;
        int[] port;
        // Storing ip_address in long (converted from String) to save space and
        // also time during comparison
        long[] ip_address;

        public Rule(char direction, char protocol, int[] port, long[] ip_address) {
            this.direction = direction;
            this.protocol = protocol;
            this.port = port;
            this.ip_address = ip_address;
        }
    }

    List<Rule> ruleList = null;
    // CSV_DELIMITER can be changed according to the delimiter used in csv file
    private static final String CSV_DELIMITER = ",";
    public Firewall(){}
    public Firewall(String filePath) throws IOException{
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(filePath));
            ruleList = new ArrayList<Rule>();

            String line = "";
            while ((line = reader.readLine()) != null){

                String[] rule = line.split(CSV_DELIMITER);
                // Assuming input file contains valid well-formed entries as specified in the document
                if(rule.length == 4){
                    // Get direction and protocol
                    char direction = rule[0].charAt(0);
                    char protocol = rule[1].charAt(0);

                    // Get port number range
                    String[] portRange = rule[2].split("-");
                    int[] port = new int[portRange.length];
                    port[0] = Integer.parseInt(portRange[0]);
                    if(portRange.length > 1){
                        port[1] = Integer.parseInt(portRange[1]);
                    }

                    // Get ip_address range
                    String[] ipRange = rule[3].split("-");
                    long[] ip = new long[ipRange.length];
                    ip[0] = ipToLong(ipRange[0]);
                    if(ipRange.length > 1){
                        ip[1] = ipToLong(ipRange[1]);
                    }

                    ruleList.add(new Rule(direction, protocol, port, ip));

                }
            }
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
        finally {
            try{
                reader.close();
            }
            catch (IOException ioe){
                System.out.println("Error occurred while closing the BufferedReader");
                ioe.printStackTrace();
            }
        }
    }

    private long ipToLong(String ip){

        String[] octets = ip.split("\\.");
        long result = 0;
        for(String oc: octets){
            short val = Short.parseShort(oc);
            if(val >= 0 && val <= 255){
                result <<= 8;
                result |=  val & 0xff;
            }
        }
        return result;

    }

    public boolean accept_packet(String direction, String protocol, int port, String ip_address){

        char dir = direction.charAt(0);
        char proto = protocol.charAt(0);
        long ip = ipToLong(ip_address);

        for(Rule rule: ruleList){
            if(rule.direction == dir && rule.protocol == proto &&
                    ((rule.port.length == 1 && rule.port[0] == port) ||
                            (rule.port.length == 2 && port >= rule.port[0] && port <= rule.port[1])) &&
                    ((rule.ip_address.length == 1 && rule.ip_address[0] == ip) ||
                            (rule.ip_address.length == 2 && ip >= rule.ip_address[0] && ip <= rule.ip_address[1]))){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        try {
            Firewall f = new Firewall("/home/hadoop/illumio/firewall-illumio/test.csv");
            System.out.println(f.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
            System.out.println(f.accept_packet("inbound", "udp", 53, "192.168.2.1"));
            System.out.println(f.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
            System.out.println(f.accept_packet("inbound", "tcp", 81, "192.168.1.2"));
            System.out.println(f.accept_packet("inbound", "udp", 24, "52.12.48.92"));

        }
        catch (IOException ie){
            System.out.println("Error occurred during CSV file read");
            ie.printStackTrace();
        }



    }
}
