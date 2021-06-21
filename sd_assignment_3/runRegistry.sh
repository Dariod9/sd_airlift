MACHINE=sd304@l040101-ws04.ua.pt
Register="localhost"
port=22330

echo "Initializing Registry."
gnome-terminal --title="RMI Registry" -- sh -c "sshpass -f password ssh $MACHINE './set_rmiregistry.sh $port'; read line"
gnome-terminal --title="Registry" -- sh -c "sshpass -f password ssh $MACHINE 'cd test/AirLift/dir_registry; ./registry_com.sh $Register $port '; read line"
