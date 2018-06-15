package com.env.sample.demospringint;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.FixedSubscriberChannel;
import org.springframework.integration.dsl.Channels;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.mapping.OutboundMessageMapper;
import org.springframework.messaging.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.env.sample.demospringint.svc.EchoImpl;
import com.env.sample.demospringint.svc.EchoService;

import java.util.Collection;
import java.util.Locale;

/**
 * @author Vamsi Vegi
 * @date 6/14/2018
 */

@Configuration
public class MyConfig {



  @Bean("requestChannelOneHello")
  public DirectChannel requestChannelOneHello() {
    return new DirectChannel();
  }

  
  @Bean
  public IntegrationFlow simpleFlowHello() {
    return IntegrationFlows.from(requestChannelOneHello())
        .transform((String s) -> s.toUpperCase())
        .get();
  }

  // ----------------- for MsgGtwy ----------------------

  @MessagingGateway(defaultRequestChannel = "simpleFlowHelloWGateWay.input")
  public interface GtwyReqReply{
    String sendRecv(String msg);
  }

  @Bean
  public IntegrationFlow simpleFlowHelloWGateWay() {
    return f -> f
        .transform((String s) -> s.toUpperCase())
        .handle(String.class, (pay, head) -> pay + "-GTWY-");
  }


  // ------------------------- for SubFlow ---------------------------


  @MessagingGateway(defaultRequestChannel = "simpleFlowHelloWGateWayWithSubFlow.input")
  public interface GtwyReqReplyWithSubFlow{
    String sendRecv(String msg);
  }


  @Bean("requestChannelForSubFlow")
  public DirectChannel requestChannelForSubFlow() {
    return new DirectChannel();
  }

  @Bean
  public IntegrationFlow simpleFlowHelloWGateWayWithSubFlow() {
    return f -> f
        .transform((String s) -> s.toUpperCase())
        .handle(String.class, (pay, head) -> pay + "-GTWY enahnced here @ Main Flow-")
        .channel(requestChannelForSubFlow());
  }

  @Bean
  public IntegrationFlow simpleSubFlow() {
    return IntegrationFlows.from("requestChannelForSubFlow")
        .transform((String s) -> s + " enahnced here @ Sub Flow ")
        .get();
  }

}
