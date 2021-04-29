FROM mysql:5.7

RUN mkdir /var/lib/mysql-files

ENV MYSQL_RANDOM_ROOT_PASSWORD=1
ENV MYSQL_DATABASE=ADV21
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=xxxxxx
ENV MYSQL_ROOT_PASSWORD: root

COPY mysqld.conf /etc/mysql/conf.d/my.cnf