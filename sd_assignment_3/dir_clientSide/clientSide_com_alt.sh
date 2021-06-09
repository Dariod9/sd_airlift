java -cp ".:../genclass.jar"\
     -Djava.rmi.server.codebase="file:///home/valente/Desktop/test/AirLift/dir_clientSide/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     clientSide.$1
