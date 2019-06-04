package com.newstracker.top;

import java.util.Arrays;
import java.util.List;

public class ServerList {

    // Enum a server llist to iterate through so that you can actually get the
    // ah data to put into the mySQL Page
    List<String> SERVERS = Arrays.asList("Zul'jin","Thrall","Hyjal","Tichondrius");

    public List<String> getSERVERS() {
        return SERVERS;
    }
}
