java -cp .:../genclass.jar -Djava.rmi.server.codebase="file:///home/valente/Desktop/test/AirLift/dir_registry/"\
     -Djava.rmi.server.useCodebaseOnly=true\
     -Djava.security.policy=java.policy\
     registry.ServerRegisterRemoteObject
