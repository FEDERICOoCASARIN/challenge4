/**
 * LongestPrefixMatcher.java
 * <p>
 * Version: 2019-07-10
 * Copyright University of Twente, 2015-2025
 * <p>
 * *************************************************************************
 * = Copyright notice =                          *
 * *
 * This file may  ONLY  be distributed UNMODIFIED              *
 * In particular, a correct solution to the challenge must  NOT be posted *
 * in public places, to preserve the learning effect for future students. *
 * *************************************************************************
 */

package lpm;

public class LongestPrefixMatcher {
    /**
     * You can use this function to initialize variables.
     */
    //        int mask = 0xFFFFFFFF << (32 - prefixLength);
    private int[] ips;
    private int[] masks;
    private int[] portNumbers;
    private int index;

    public LongestPrefixMatcher() {
        ips = new int[420972];
        masks = new int[420972];
        portNumbers = new int[420972];
        index = 0;
    }

    /**
     * Looks up an IP address in the routing tables.
     * @param ip The IP address to be looked up in integer representation
     * @return The port number this IP maps to
     */
    public int lookup(int ip) {
        // TODO: Look up this route
        for (int i = 420971; i >= 0 ; i--) {
            if ((ip & masks[i]) == ips[i]) {
//                System.out.println(ipToHuman(ips[i]));
//                System.out.println(ips[i]);
                return portNumbers[i];
            }
        }
//        System.out.println(-1);
        return -1;
    }

    /**
     * Adds a route to the routing tables.
     * @param ip The IP the block starts at in integer representation
     * @param prefixLength The number of bits indicating the network part
     *                     of the address range (notation ip/prefixLength)
     * @param portNumber The port number the IP block should route to
     */
    public void addRoute(int ip, byte prefixLength, int portNumber) {
        // TODO: Store this route for later use in lookup() method
        //If they are the same, choose the one with the largest prefix
        //add an if the previous is the same IP, check for the prefix and if greater change either way just continue.
        ips[index] = ip;
        masks[index] = 0xFFFFFFFF << (32 - prefixLength);
        portNumbers[index] = portNumber;
        index++;
    }

    /**
     * This method is called after all routes have been added.
     * You don't have to use this method but can use it to sort or otherwise
     * organize the routing information, if your datastructure requires this.
     */
    public void finalizeRoutes() {
    }

    /**
     * Converts an integer representation IP to the human readable form.
     * @param ip The IP address to convert
     * @return The String representation for the IP (as xxx.xxx.xxx.xxx)
     */
    private String ipToHuman(int ip) {
        return Integer.toString(ip >> 24 & 0xff) + "." + Integer.toString(ip >> 16 & 0xff) + "." +
                Integer.toString(ip >> 8 & 0xff) + "." + Integer.toString(ip & 0xff);
    }

    /**
     * Parses an IP.
     * @param ipString The IP address to convert
     * @return The integer representation for the IP
     */
    private int parseIP(String ipString) {
        String[] ipParts = ipString.split("\\.");

        int ip = 0;
        for (int i = 0; i < 4; i++) {
            ip |= Integer.parseInt(ipParts[i]) << (24 - (8 * i));
        }

        return ip;
    }
}