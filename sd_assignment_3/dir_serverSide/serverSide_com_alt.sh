java -cp ".:../genclass.jar"\
     -Djava.rmi.server.codebase="file:///home/valente/Desktop/test/AirLift/dir_serverSide/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     serverSide.$1
