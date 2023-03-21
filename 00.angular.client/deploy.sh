cd dist/angular.peters-perfect-permutation
tar -cf ../../ppp.tar *
scp ../../ppp.tar root@electronicpeter.de:/var/www/vhosts/electronicpeter.de/perfect.permutation.electronicpeter.de
ssh root@electronicpeter.de tar -C /var/www/vhosts/electronicpeter.de/perfect.permutation.electronicpeter.de -xf /var/www/vhosts/electronicpeter.de/perfect.permutation.electronicpeter.de/ppp.tar
cd -
