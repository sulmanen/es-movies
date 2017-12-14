# Hands on Elasticsearch
Elasticsearch is a good fulltext search engine.

- Wikipedia search is powered by Elasticsearch.
- The Guardian joins access log data with social network data using Elasticsearch to give editors an idea of how public is reponding to articles.
- StackOverflow fulltext search is powered by Elasticsearch. _more like this_ to find similar answers.
- GitHub uses Elasticsearch to query 130 billion lines of code

## Prerequisites
Docker and Python 2.7 with pip or easy_istall and internet access.

## Apache Lucene
Full-text search engine. Elasticsearch uses [Lucene's Practical Scoring Function](https://www.elastic.co/guide/en/elasticsearch/guide/current/practical-scoring-function.html) by default.

Pluggable scoring functions like the Vector Space Model and [Okapi BM 25](https://www.elastic.co/guide/en/elasticsearch/guide/current/pluggable-similarites.html#bm25).

## Elasticsearch
Elasticsearch is Apache Lucene wrapped in a scalable and relatively easy to use RESTful interface. 

## Excercises
We are using [UCI Movies Dataset](https://archive.ics.uci.edu/ml/datasets/Movie) of over 10k films. The titles are from late 1800's to 1999.

0. Get code. ```git clone ```
1. Fire up elasticsearch. ```docker-compose up```
2. Verify. ```curl http://localhost:9200```
3. Deps. ```pip install requests
      pip install BeautifulSoup```
4. Load data using es _bulk interface in nd-json format. ```python2.7 import-movies.py``` 

### [URI Search](https://www.elastic.co/guide/en/elasticsearch/reference/current/search-uri-request.html)
Find all the Academy Awards winners in the database. _AA_ stands for winning an Academy Award.

Find the film _Elmer Gantry_ in the raw data. Did it win an Academy Award? 

###[Boolean Query](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-bool-query.html)
