MACHINE=sd304@l040101-ws04.ua.pt

echo "Compiling source code."
javac -cp "../genclass.jar" utils/*.java interfaces/*.java registry/*.java serverSide/*.java clientSide/*.java
echo "Distributing intermediate code to the different execution environments."
cp utils/*.class dir_serverSide/utils/
cp utils/*.class dir_clientSide/utils/
cp clientSide/Passenger.class dir_serverSide/clientSide
cp clientSide/Pilot.class dir_serverSide/clientSide
cp clientSide/HostessStates.class clientSide/PassengerStates.class clientSide/PilotStates.class dir_serverSide/clientSide
cp interfaces/Register.class dir_registry/interfaces/
cp registry/*.class dir_registry/registry/
cp interfaces/*.class dir_serverSide/interfaces/
cp serverSide/*.class dir_serverSide/serverSide/
cp serverSide/*.class dir_serverSide/serverSide/
cp interfaces/AirplaneInt.class interfaces/DepAirportInt.class interfaces/DestAirportInt.class interfaces/RepositoryInt.class dir_clientSide/interfaces/
cp clientSide/*.class dir_clientSide/clientSide/
cp clientSide/*.class dir_clientSide/clientSide/
sshpass -f password ssh $MACHINE 'mkdir -p Public/classes'
sshpass -f password ssh $MACHINE 'mkdir -p Public/classes/interfaces'
sshpass -f password ssh $MACHINE 'mkdir -p Public/classes/clientSide/'
sshpass -f password ssh $MACHINE 'mkdir -p Public/classes/serverSide/'
sshpass -f password scp interfaces/*.class $MACHINE:Public/classes/interfaces
sshpass -f password scp clientSide/*.class $MACHINE:Public/classes/clientSide
sshpass -f password scp serverSide/*.class $MACHINE:Public/classes/serverSide
echo "Compressing execution environments."
rm -f dir_registry.zip dir_serverSide.zip dir_clientSide.zip
zip -rq dir_registry.zip dir_registry
zip -rq dir_serverSide.zip dir_serverSide
zip -rq dir_clientSide.zip dir_clientSide
echo "Deploying and decompressing execution environments."
sshpass -f password scp set_rmiregistry.sh $MACHINE:
sshpass -f password scp password $MACHINE:
sshpass -f password ssh $MACHINE 'mkdir -p test/AirLift'
sshpass -f password ssh $MACHINE 'rm -rf test/AirLift/*'
sshpass -f password scp dir_registry.zip dir_serverSide.zip dir_clientSide.zip genclass.jar  $MACHINE:test/AirLift
sshpass -f password ssh $MACHINE 'cd test/AirLift; unzip -uq dir_registry.zip; unzip -uq dir_serverSide.zip; unzip -uq dir_clientSide.zip'

