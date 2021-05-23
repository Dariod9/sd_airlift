echo "Transfering data to the DepAirport node."
sshpass -f password ssh sd304@l040101-ws05.ua.pt 'mkdir -p test/AirLift'
sshpass -f password ssh sd304@l040101-ws05.ua.pt 'rm -rf test/AirLift/*'
sshpass -f password scp dirAirLift.zip sd304@l040101-ws05.ua.pt:test/AirLift
echo "Decompressing data sent to the DepAirport node."
sshpass -f password ssh sd304@l040101-ws05.ua.pt 'cd test/AirLift ; unzip -uq dirAirLift.zip'
echo "Executing program at the DepAirport node."
sshpass -f password ssh sd304@l040101-ws05.ua.pt 'cd test/AirLift/ ; java -cp .:./genclass.jar serverSide.main.DepAirportMain'