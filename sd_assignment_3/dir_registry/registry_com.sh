java -cp ".:../genclass.jar"\
     -Djava.rmi.server.codebase="file:///home/sd304/Public/classes/"\
     -Djava.rmi.server.useCodebaseOnly=true\
     -Djava.security.policy=java.policy\
     registry.ServerRegisterRemoteObject
