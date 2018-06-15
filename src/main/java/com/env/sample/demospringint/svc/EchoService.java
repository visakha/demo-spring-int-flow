package com.env.sample.demospringint.svc;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * @author Vamsi Vegi
 * @date 6/14/2018
 */
@MessagingGateway
public interface EchoService {
  @Gateway(requestChannel = "requestChannel")
  String echo(String message);
}