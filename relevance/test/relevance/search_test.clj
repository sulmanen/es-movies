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
              (map (fn [hit] (read-string (subs (get-in hit ["_source" "year"]) 0 4))) hits))
    true
    false))

(defn pluck [hits field]
  (map (fn [hit] (get-in hit ["_source" field])) hits))


(deftest can-query-title
  (testing "We can query for pulp and get results"
    (is (= (pluck (get-hits (search! "pulp" 0 6)) "title")
           ["Pulp Fiction"]))))

(deftest can-query-by-director
  (testing "Can query by director"
    (is (= (pluck (get-hits (search! "Hitchcock" 0 6)) "director")
           ["T.Demme"
            "Hitchcock"
            "Hitchcock"
            "Hitchcock"
            "Hitchcock"
            "Hitchcock"]))))

(deftest can-query-by-title
  (testing "Can query by title"
    (is (= (pluck (get-hits (search! "sound" 0 6)) "title")
           ["The Sound of Music"
            "The Sound and the Fury"
            "The Sound Barrier"
]))))

(deftest can-query-by-year
  (testing "Can query by title"
    (is (= (pluck (get-hits (search! "1977" 0 6)) "title")
           ["L'Diable probalement"
            "The Spy Who Loved Me"
            "Man of Marble" "Matababi"
            "The Island of Dr.~Moreau"
            "Carry On Emanuelle"]))))

(deftest can-query-by-partial-year
  (testing "Can query by title"
    (is (= (pluck (get-hits (search! "197" 0 6)) "title")
           ["Dreamer"
            "The Innocent"
            "Mysterious Island of Beautiful Women"
            "All That Jazz"
            "The Young Girls of Wilko"
            "10"]))))

(deftest ordered-by-year
  (testing "results ordered by year latest first"
    (is (ordered (get-hits (search! "woman" 0 6))) true)))
