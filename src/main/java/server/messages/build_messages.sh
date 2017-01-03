protoc --java_out=.. *.proto
mv ../server/messages/* .
rm -rf ../server
