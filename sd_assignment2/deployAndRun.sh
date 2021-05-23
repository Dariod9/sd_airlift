xterm  -T "General Repository" -hold -e "./RepositoryDeployAndRun.sh" &
xterm  -T "Departure Airport" -hold -e "./DepAirportDeployAndRun.sh" &
xterm  -T "Destination Airport" -hold -e "./DestAirportDeployAndRun.sh" &
xterm  -T "Plane" -hold -e "./AirplaneDeployAndRun.sh" &
sleep 1
xterm  -T "Pilot" -hold -e "./PilotDeployAndRun.sh" &
xterm  -T "Hostess" -hold -e "./HostessDeployAndRun.sh" &
xterm  -T "Passengers" -hold -e "./PassengersDeployAndRun.sh" &
