(ns relevance.search-test
  (:require [clojure.test :refer :all]
            [relevance.core :refer :all]
            [relevance.search :refer [search!]]))

(defn get-first-hit [res]
  (get-in (first (get-in res ["hits" "hits"])) ["_source" "title"]))

(deftest can-query
  (testing "We can query for woman and get results"
    (is (= (get-first-hit (search! "woman" 0 25)) "Sherlock Holmes and the Spider Woman"))))
