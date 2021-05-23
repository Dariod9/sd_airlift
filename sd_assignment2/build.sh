javac -cp "../genclass.jar" @sources.txt
rm -f  dirAirLift.zip
zip -rq dirAirLift.zip serverSide clientSide commInfra ../genclass.jar
