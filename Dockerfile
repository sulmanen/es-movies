
FROM docker.elastic.co/elasticsearch/elasticsearch:6.1.0

ADD ./elasticsearch.yml /usr/share/elasticsearch/config/
