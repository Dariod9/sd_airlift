#!/bin/bash
MACHINE=sd304@l040101-ws04.ua.pt
Register="localhost"
port=22330

CLASSES=(
  PilotMain
  HostessMain
  PassengerMain
)

echo "Initializing Client Side Entities"
for i in "${CLASSES[@]}"; do
  gnome-terminal --tab --title="$i" -- sh -c "sshpass -f password ssh $MACHINE 'cd test/AirLift/dir_clientSide; ./clientSide_com.sh $i $Register $port; read line'"
  sleep 0.5
done
