(ns relevance.search-test
  (:require [clojure.test :refer :all]
            [relevance.core :refer :all]
            [relevance.search :refer [search!]]))

(defn get-hits [res]
  (get-in res ["hits" "hits"]))

(defn get-nth [res field n]
  (get-in (nth (get-hits res) n) ["_source" field]))

(defn get-first-hit [res field]
  (get-nth res field 0))

(defn ordered [hits]
  (if (reduce (fn [first second]
                (if (and first (>= first second)) second false))
              (map (fn [hit] (read-string (subs (get-in hit ["_source" "year"]) 0 4))) hits)) true false))

(deftest can-query
  (testing "We can query for pulp and get results"
    (is (= (get-first-hit (search! "pulp" 0 6) "title")
           "Pulp Fiction"))))

(deftest can-query-by-director
  (testing "Can query by director"
    (is (= (get-first-hit (search! "tarantino" 0 6) "director")
           "Tarantino"))))

(deftest query-by-year
  (testing "query by year"
    (is (= (get-first-hit (search! "1977" 0 6) "year")
           "1977-01-01T00:00:00"))))

(deftest ordered-by-year
  (testing "results ordered by year latest first"
    (is (= (ordered (get-hits (search! "woman" 0 6))) true))))
