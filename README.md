# Spring Boot Integration Sample
Using the Rest Controller to innteract with outside world
and Spring Integration as a callable library to allow message oriented paradigm

The overall set up of this application is as follows 
            
            -> Web Browser (or Client) makes a HTTP Request
            -> HTTP request intercepted by Rest Controller
            -> Rest Controller sends a msg to the channel
                -> Spring Int flow initiated automatically
                -> get response from the flow
            -> show response to Browser


case 1: 
    use MessageTemplate builder (send / recieve)
    
    
case 2: 
    use MessageGateway and the flow builder will reply back to this
    

case 3: 
    same as use case 2, but there is subflow and still will reply back to the GateWay
    
        
        