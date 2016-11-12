# WoW-auction-profits-scanner

Web app that displays minimum buyouts and profit percentages for WoW items.

[![Example](http://i.imgur.com/hKehOmS.png)]()

# Configuration

Open config.properties to setup your realm information and add the IDs of the items you want to track. You can find them at Wowhead's urls, like http://www.wowhead.com/item=124105/starlight-rose (Starlight Rose's id is 124105)

# Running

On your terminal:
```swift
./gradlew run
```
After "Auction data updated." is displayed, the information will be available at your localhost: http://localhost:8080/
