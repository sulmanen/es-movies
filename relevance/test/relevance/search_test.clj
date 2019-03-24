(ns relevance.search-test
  (:require [clojure.test :refer :all]
            [relevance.core :refer :all]
            [relevance.search :refer [search!]]))

(defn get-first-hit [res field]
  (get-in (first (get-in res ["hits" "hits"])) ["_source" field]))

(deftest can-query
  (testing "We can query for woman and get results"
    (is (= (get-first-hit (search! "woman" 0 25) "title") "Woman to Woman"))))

(deftest can-query-by-director
  (testing "Can query by director"
    (is (= (get-first-hit (search! "tarantino" 0 25) "director") "Tarantino"))))

(deftest query-by-year
  (testing "query by year"
    (is (= (get-first-hit (search! "1977" 0 25) "year") "1977"))))
