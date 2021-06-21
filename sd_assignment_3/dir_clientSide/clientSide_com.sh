java -cp ".:../genclass.jar"\
     -Djava.rmi.server.codebase="file:///home/sd304/test/AirLift/dir_clientSide/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     clientSide.$1 "$2" "$3"