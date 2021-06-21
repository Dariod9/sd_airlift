java -cp ".:../genclass.jar"\
     -Djava.rmi.server.codebase="file:///home/sd304/test/AirLift/dir_serverSide/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     serverSide.$1 "$2" "$3" "$4"


