#!/bin/bash

echo "Executing all the shared regions and entities..............."
gnome-terminal -t "Repo Main" --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.RepositoryMain; bash"
gnome-terminal -t "Dep Airport Main" --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.DepAirportMain; bash"
gnome-terminal  -t "Dest Airport Main" --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.DestAirportMain; bash"
gnome-terminal -t "Airplane Main" --tab -- sh -c "java -cp .:../../genclass.jar serverSide.main.AirplaneMain; bash"
gnome-terminal -t "Pilot Main" --tab -- sh -c "java -cp .:../../genclass.jar clientSide.main.PilotMain; bash"
gnome-terminal -t "Hostess Main" --tab -- sh -c "java -cp .:../../genclass.jar clientSide.main.HostessMain; bash"
gnome-terminal -t "Passenger Main" --tab -- sh -c "java -cp .:../../genclass.jar clientSide.main.PassengerMain; bash"