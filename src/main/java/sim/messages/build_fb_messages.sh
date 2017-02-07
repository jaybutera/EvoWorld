flatc --java *.fbs
rm -rf AI/
mv ./sim/messages/* .
rm -rf ./sim
