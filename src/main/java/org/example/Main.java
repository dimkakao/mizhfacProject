package org.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j(topic = "dfdf.dfdf.dfdfdfdfdff.dfdfd.dfdfdfdbgbgbg")
public class Main {

//    static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        log.trace("log trace");
        log.debug("log debug");
        log.info("log info");
        log.warn("log warn");
        log.error("log error");
        System.out.println("URA");
    }
}