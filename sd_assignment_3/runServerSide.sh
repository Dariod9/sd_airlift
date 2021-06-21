#!/bin/bash
MACHINE=sd304@l040101-ws04.ua.pt
Register="localhost"
port=22330

CLASSES=(
  RepositoryMain
  DepAirportMain
  DestAirportMain
  AirplaneMain
)

PORTS=(
  "22334"
  "22331"
  "22333"
  "22332"
)

echo "Executing program Servers"
for i in ${!CLASSES[@]}; do
    gnome-terminal --tab --title="${CLASSES[$i]}" -- sh -c "sshpass -f password ssh $MACHINE 'cd test/AirLift/dir_serverSide; ./serverSide_com.sh ${CLASSES[$i]} $Register $port ${PORTS[$i]}; read line'"
    sleep 1
done