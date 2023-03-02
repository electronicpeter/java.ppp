# Peters Perfect Permutation Angular CLIENT

After updating the package json, two components have to be added

    npm install @angular/material
    npm install @openapitools/openapi-generator-cli

To get Material correctly showing all components (for example
the shadow for the material-cards) its important to add a 
theme in the file <code>styles.css</code>

    @import "~@angular/material/prebuilt-themes/indigo-pink.css";

If the api has been changed, the regeneration of the client code
in the angular client works with
       
    npm run openapi-gen

Most attributes habe been set to <code>required</code> to
avoid setting the questionmark in the html files

The url defining the target of the restcall is defined in line 89 in 
    
    src/app/api/configuration.ts 



