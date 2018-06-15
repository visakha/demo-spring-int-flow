package com.env.sample.demospringint.web;

import com.env.sample.demospringint.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.endpoint.EventDrivenConsumer;
import org.springframework.integration.mapping.OutboundMessageMapper;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.env.sample.demospringint.svc.EchoService;

/**
 * @author Vamsi Vegi
 * @date 6/14/2018
 */

@RestController
public class EndPoints {


  @Autowired
  DirectChannel requestChannelHello;

  /**
   * http://localhost:8080/hello
   * you will see: hello TEST
   * @return
   */
  @GetMapping("/hello")
  public String hello(){
	  MessagingTemplate template = new MessagingTemplate();

	  Message reply = template.sendAndReceive(requestChannelHello, new GenericMessage("test"));
      return "hello " +  (String) reply.getPayload();
  }



  @Autowired
  MyConfig.GtwyReqReply gtwyReqReply;

  /**
   * http://localhost:8080/helloGtwy
   * you will see: hello USING -GTWY-
   * @return
   */
  @GetMapping("/helloGtwy")
  public String hello2(){

    return "hello " +  gtwyReqReply.sendRecv("Using ");
  }


}
