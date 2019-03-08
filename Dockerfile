FROM docker.elastic.co/elasticsearch/elasticsearch:6.6.1@sha256:b3c3863bfef1bdc79ebb55e61a5f140b397cc6406638b42b7b624ef9742bece3

ADD ./elasticsearch.yml /usr/share/elasticsearch/config/
