FROM docker.elastic.co/elasticsearch/elasticsearch:7.0.0

ADD ./elasticsearch.yml /usr/share/elasticsearch/config/
