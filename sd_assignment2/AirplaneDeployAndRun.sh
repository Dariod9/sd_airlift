echo "Transfering data to the Airplane node."
sshpass -f password ssh sd304@l040101-ws04.ua.pt 'mkdir -p test/AirLift'
sshpass -f password ssh sd304@l040101-ws04.ua.pt 'rm -rf test/AirLift/*'
sshpass -f password scp dirAirLift.zip sd304@l040101-ws04.ua.pt:test/AirLift
echo "Decompressing data sent to the Airplane node."
sshpass -f password ssh sd304@l040101-ws04.ua.pt 'cd test/AirLift ; unzip -uq dirAirLift.zip'
echo "Executing program at the Airplane node."
sshpass -f password ssh sd304@l040101-ws04.ua.pt 'cd test/AirLift/ ; java -cp .:./genclass.jar serverSide.main.AirplaneMain'