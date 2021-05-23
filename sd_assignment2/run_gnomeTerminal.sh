#!/bin/bash

echo "Executing all the shared regions and entities..............."
gnome-terminal --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.RepositoryMain; bash"
gnome-terminal --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.DepAirportMain; bash"
gnome-terminal --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.DestAirportMain; bash"
gnome-terminal --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.AirplaneMain; bash"
gnome-terminal --tab -- sh -c "java -cp .:../../genclass.jar clientSide.main.PilotMain; bash"
gnome-terminal --tab -- sh -c "java -cp .:../../genclass.jar clientSide.main.HostessMain; bash"
gnome-terminal --tab -- sh -c "java -cp .:../../genclass.jar clientSide.main.PassengerMain; bash"