(ns relevance.search-test
  (:require [clojure.test :refer :all]
            [relevance.core :refer :all]
            [relevance.search :refer [search!]]))

(defn query [q] {
  "query" {
    "bool" {
      "filter" {
        "term" {
          "title" q
        }
      }
    }
  }
})

(deftest a-test
  (testing "FIXME, I fail."
    (is (= (search! (query "woman") 0 25) 1))))
