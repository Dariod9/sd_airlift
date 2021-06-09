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
mkdir -p /home/valente/Desktop/Public/classes
mkdir -p /home/valente/Desktop/Public/classes/interfaces
mkdir -p /home/valente/Desktop/Public/classes/clientSide/
mkdir -p /home/valente/Desktop/Public/classes/serverSide/
cp interfaces/*.class /home/valente/Desktop/Public/classes/interfaces/
cp clientSide/*.class /home/valente/Desktop/Public/classes/clientSide/
cp serverSide/*.class /home/valente/Desktop/Public/classes/serverSide/
echo "Compressing execution environments."
rm -f dir_registry.zip dir_serverSide.zip dir_clientSide.zip
zip -rq dir_registry.zip dir_registry
zip -rq dir_serverSide.zip dir_serverSide
zip -rq dir_clientSide.zip dir_clientSide
echo "Deploying and decompressing execution environments."
cp set_rmiregistry_alt.sh /home/valente/Desktop
cp set_rmiregistry.sh /home/valente/Desktop
mkdir -p /home/valente/Desktop/test/AirLift
rm -rf /home/valente/Desktop/test/AirLift/*
cp dir_registry.zip dir_serverSide.zip dir_clientSide.zip /home/valente/Desktop/test/AirLift
cd /home/valente/Desktop/test/AirLift
unzip -q dir_registry.zip
unzip -q dir_serverSide.zip
unzip -q dir_clientSide.zip
