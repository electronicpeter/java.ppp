# PetersPerfectPermutation

Zum default package.json nachträglich

    npm install @angular/material
    npm install @openapitools/openapi-generator-cli

Damit Material auch z.B. die Rahmen der Karten anzeigt, muss im styles.css ein Material Schema eingebunden sein

    @import "~@angular/material/prebuilt-themes/indigo-pink.css";

Zum Generieren der API-Sourcen das target
       
    npm run openapi-gen

aufrufen. Hier wurden diverse Attribute auf madatory gesetzt, damit
im HTML-Code nicht überall ein ? dahinter gesetzt werden muss.

Die URL, an die die REST-Calls gehen, ist in Zeile 90 hinterlegt in
    
    src/app/api/configuration.ts 

Ein kompletter build mit

     mvn clean install -Pfull

Danach einfach mit 

    deploy.sh
das angular und das server projekt separat deployent



