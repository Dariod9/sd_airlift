#!/bin/bash

echo "Executing all the shared regions and entities..............."
gnome-terminal -e "java serverSide/main/RepositoryMain" -t "mainRepo"
gnome-terminal -e "java serverSide/main/AirplaneMain" -t "mainAirplane"
gnome-terminal -e "java serverSide/main/DepAirportMain" -t "mainDepAirport"
gnome-terminal -e "java serverSide/main/mainDestAirportMain" -t "mainDestAirport"
gnome-terminal -e "java clientSide/main/HostessMain" -t "mainHostess"
gnome-terminal -e "java clientSide/main/PassengerMain" -t "mainPassenger"
gnome-terminal -e "java clientSide/main/PilotMain" -t "mainPilot"