# pi-rest
sample api rest

## generate jar
mvn clean compile assembly:single

## execute script
bash scripts/launchPiApp.bash

## sample rest request

curl -H "Content-Type: application/json" -X POST -d '{"content":["Are the kids at home? aaaaa fffff","Yes they are here! aaaaa fffff"]}' http://localhost:4567/combine
<br/>  
#### request components
url: http://localhost:4567/combine  
headers: content-type application/json  
Body  
{   
"content":[  
    "Are the kids at home? aaaaa fffff",  
    "Yes they are here! aaaaa fffff"]  
}  

"{\"mix\":\"=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh\"}"
