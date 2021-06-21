java -cp ".:../genclass.jar"\
     -Djava.rmi.server.codebase="file:///home/sd304/test/AirLift/dir_registry/"\
     -Djava.rmi.server.useCodebaseOnly=false \
     -Djava.security.policy=java.policy\
     registry.ServerRegisterRemoteObject "$1" "$2"
