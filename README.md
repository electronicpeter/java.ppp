# Peters Perfect Permutation

Requirement:
* java11
* ng 19.6.1
* npm 9.4.0

The backend is straigh forward a <code>spring-boot</code>
component. The client is angluar. For more details to the client
have a look at [00.angular.client/README.md](00.angular.client/README.md)

A complete build of frontend and backend can be done with

     mvn clean install -Pfull

After this, the two components (client frontend and server backend) can be deployed with

    deploy.sh

