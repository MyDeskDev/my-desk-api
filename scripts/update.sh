JAR_FILE=$(ls -tr *.jar | tail -n 1)
date=$(date '+%Y%m%d%H%M%S')
IMAGE_NAME=$JAR_FILE$date

echo "> 새로운 이미지($IMAGE_NAME)  빌드"
docker build --build-arg JAR_FILE="$JAR_FILE" -t $IMAGE_NAME .

echo "> 기존 container 찾기"
CONTAINER_ID=$(docker ps | grep mydesk-api | awk '{ print $1 }')

echo "> stop & remove"
docker stop $CONTAINER_ID && docker rm $CONT정AINER_ID

echo "> 새로운 container 띄우기"
NEW_CONTAINER_ID=$(docker container run -d -p 8080:8080 --name=mydesk $IMAGE_NAME)

echo "> 성공적으로 띄워졌는지 확인"
sleep 5
RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080)

if [ $RESPONSE_CODE -lt 400 ]; then
        echo "> Success"
        echo $NEW_CONTAINER_ID
else
        echo "> Fail"
fi
