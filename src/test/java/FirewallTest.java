package com.illumio.code;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirewallTest {

    @org.junit.jupiter.api.Test
    void accept_packet() {
        try {
            Firewall f = new Firewall("/home/hadoop/illumio/firewall-illumio/test.csv");
            boolean result1 = f.accept_packet("inbound", "tcp", 80, "192.168.1.2");
            assertEquals(true, result1);

            boolean result2 = f.accept_packet("inbound", "udp", 53, "192.168.2.1");
            assertEquals(true, result2);

            boolean result3 = f.accept_packet("outbound", "tcp", 10234, "192.168.10.11");
            assertEquals(true, result3);

            boolean result4 = f.accept_packet("inbound", "tcp", 81, "192.168.1.2");
            assertEquals(false, result4);

            boolean result5 = f.accept_packet("inbound", "udp", 24, "52.12.48.92");
            assertEquals(false, result5);

        }
        catch (IOException ie){
            System.out.println("Error occurred during CSV file read");
            ie.printStackTrace();
        }
    }
}
