java -cp ".:../genclass.jar"\
     -Djava.rmi.server.codebase="file:///home/sd304/Public/classes/"\
     -Djava.rmi.server.useCodebaseOnly=false\
     -Djava.security.policy=java.policy\
     clientSide.$1