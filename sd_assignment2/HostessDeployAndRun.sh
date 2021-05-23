echo "Transfering data to the Hostess node."
sshpass -f password ssh sd304@l040101-ws02.ua.pt 'mkdir -p test/AirLift'
sshpass -f password ssh sd304@l040101-ws02.ua.pt 'rm -rf test/AirLift/*'
sshpass -f password scp dirAirLift.zip sd304@l040101-ws02.ua.pt:test/AirLift
echo "Decompressing data sent to the Hostess node."
sshpass -f password ssh sd304@l040101-ws02.ua.pt 'cd test/AirLift ; unzip -uq dirAirLift.zip'
echo "Executing program at the Hostess node."
sshpass -f password ssh sd304@l040101-ws02.ua.pt 'cd test/AirLift/ ; java -cp .:./genclass.jar clientSide.main.HostessMain'