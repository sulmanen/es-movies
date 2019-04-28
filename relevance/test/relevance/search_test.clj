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

(defn ordered? [hits]
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
           ["Pulp Fiction"
            "Pump Up the Volume"
            "Pumping Iron"
            "Le pull-over rouge"
            "The Pumpkin Eater"
            "Tm:Ensign Pulver"]))))

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
            "The Sound Barrier"]))))

(deftest can-query-by-year
  (testing "Can query by title"
    (is (= (pluck (get-hits (search! "1977" 0 6)) "title")
           ["Audrey Rose"
            "Looking for Mr.~Goodbar"
            "The Choirboys"
            "Another Man, another Chance"
            "The Good and the Bad"
            "The Deep"]))))

(deftest can-query-by-partial-year
  (testing "Can query by title"
    (is (= (pluck (get-hits (search! "197" 0 6)) "title")
           ["Wise Blood"
            "Tm:Escape from Alcatraz"
            "Tm:Orca the Killer Whale"
            "Tm:The Martian Chronicles I/II/III"
            "Een Vrouw tussen Hond en Wolf"
            "Norma Rae"]))))

(deftest ordered-by-year
  (testing "results ordered by year latest first"
    (is (ordered? (get-hits (search! "woman" 0 6))) true)))


(deftest can-typo
  (testing "can typo by one character starting with the first two characters"
    (is (= (pluck (get-hits (search! "Hichcock" 0 6)) "director")
           ["T.Demme"
            "Hitchcock"
            "Hitchcock"
            "Hitchcock"
            "Hitchcock"
            "Hitchcock"]))))
