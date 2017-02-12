### To compile protocol buffers
Move to directory src/main/java/server/messages and run build_messages.h. It's
important to actually be in the messages directory when running the script.

```
$ cd src/main/java/server/messages
$ sh build_fb_messages.sh
```

### To compile flat buffers
```
$ cd src/main/java/sim
$ sh build_messages.sh
```

### To run scene with visuals
The production game loop is in FastLoop.java. To run the PetriWorld scene with
visuals, run the Visualization.java file.
