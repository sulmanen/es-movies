(defproject relevance "0.1.0-SNAPSHOT"
  :description "Test Driven Relevance Demo"
  :url "https://github.com/sulmanen/es-movies/relevance"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.8.1"]
                 [http-kit "2.3.0"]]
  :plugins[[lein-cljfmt "0.6.1"]]
  :cljfmt {:indents {around   [[:inner 0]]
                     context  [[:inner 0]]
                     describe [[:inner 0]]
                     it       [[:inner 0]]
                     should=  [[:block 0]]}}
  :main ^:skip-aot relevance.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
