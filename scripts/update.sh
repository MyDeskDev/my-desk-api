echo "> 새로운 이미지 빌드"
JAR_FILE=$(ls -tr *.jar | tail -n 1)
date=$(date '+%Y%m%d%H%M%S')
docker build --build-arg JAR_FILE="$JAR_FILE" -t mydesk-api:"$date" .

echo "> 기존 container 찾기"
CONTAINER_ID=$(docker ps | awk '{ if ($2 == "mydesk-api") print ($1)}')

echo "> stop & remove"
docker stop $CONTAINER_ID && docker rm $CONTAINER_ID

echo "> 새로운 container 띄우기"
NEW_CONTAINER_ID=$(docker container run -d -p 8080:8080 mydesk-api:$date)

echo "> 성공적으로 띄워졌는지 확인"
sleep 5
RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080)

if [ $RESPONSE_CODE -lt 400 ]; then
        echo "> Success"
        echo $NEW_CONTAINER_ID
else
        echo "> Fail"
fi