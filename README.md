# Spring Boot Integration Sample
Using the Rest Controller to innteract with outside world
and Spring Integration as a callable library to allow message oriented paradigm

RestController -> Send Msg -> Spring Int flow -> get response -> show response to Browser


case 1: 
    use MessageTemplate builder (send / recieve)
    

case 2: 
    use MessageGateway builder and the flow builder will reply back to this
    

case 3: 
    same as use case 2, but there is subflow and still will reply back to the GateWay
    
        
        