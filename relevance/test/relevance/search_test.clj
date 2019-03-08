(ns relevance.search-test
  (:require [clojure.test :refer :all]
            [relevance.core :refer :all]
            [relevance.search :refer [search!]]))

(deftest can-query
  (testing "We can query for woman and get results"
    (is (= (search! "woman" 0 25) 1))))
