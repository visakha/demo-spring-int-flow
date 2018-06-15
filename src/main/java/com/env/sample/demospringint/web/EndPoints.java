package com.env.sample.demospringint.web;

import com.env.sample.demospringint.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vamsi Vegi
 * @date 6/14/2018
 */

@RestController
@RequestMapping("/spring-int-demo")
public class EndPoints {


  @Autowired
  @Qualifier("requestChannelOneHello")
  DirectChannel requestChannelOneHello;

  /**
   * Demo: Use MessageTemplate and the method sendAndReceive
   * to get the reply back
   * case 1:
   * http://localhost:8080/spring-int-demo/case1
   *
   * @return
   * hello from case 1: Reply is MSG FROM CASE 1
   */
  @GetMapping("/case1")
  public String useCaseOnehello() {
    MessagingTemplate template = new MessagingTemplate();

    Message reply = template.sendAndReceive(requestChannelOneHello, new GenericMessage("Msg from case 1"));
    return "hello from case 1: Reply is " + (String) reply.getPayload();
  }


  /**
   * Demo: Use Gatewat and make sure the flow does not instantiate its own channel
   * that way we get the reply back
   * case 2:
   * http://localhost:8080/spring-int-demo/case2
   *
   * @return
   * hello from case 2: Reply is MSG FROM CASE 2-GTWY-
   */

  @Autowired
  MyConfig.GtwyReqReply gtwyReqReply;

  @GetMapping("/case2")
  public String case2() {

    return "hello from case 2: Reply is " + gtwyReqReply.sendRecv("Msg from case 2");
  }


  /**
   * Demo: same as cas2, but the flow also has a SubFlow or a handoff flow
   * and we still get the reply back
   * case 3:
   * http://localhost:8080/spring-int-demo/case3
   *
   * @return
   * hello from case 2: Reply is MSG FROM CASE 3-GTWY enahnced here @ Main Flow- enahnced here @ Sub Flow
   */

  @Autowired
  MyConfig.GtwyReqReplyWithSubFlow gtwyReqReplyWithSubFlow;

  @GetMapping("/case3")
  public String case3() {

    return "hello from case 2: Reply is " + gtwyReqReplyWithSubFlow.sendRecv("Msg from case 3");
  }


}
