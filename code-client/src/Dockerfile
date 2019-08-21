FROM  docker-hub.kefang.net/java

ADD target/client-api.jar app.jar

# 设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

ENTRYPOINT ["java","-jar","/app.jar"]