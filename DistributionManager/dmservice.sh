START_COMMAND="start"
STOP_COMMAND="stop"

if [ "$1" = "$START_COMMAND" ] ; then
	echo "Starting distribution manager service...."
	mvn jetty:run 
	exit 0
	
fi

if [ "$1" = "$STOP_COMMAND" ] ; then
	PID=`fuser 9001/tcp`
	echo "$PID"
	if [ $PID ] ; then
		echo "Shutting down the distribution manager service."
		kill -9 $PID
	else
		echo "The distribution manager service is not running"
	fi
fi
