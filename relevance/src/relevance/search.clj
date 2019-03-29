(ns relevance.search
  (:require [clojure.string :as str]
            [cheshire.core :as json]
            [org.httpkit.client :refer [request]]))

(def options {:rest-url "http://localhost:9200" :index "movies"})
(def http-request-timeout-ms 10000)

(defn- search-url [{:keys [rest-url index]}]
  (str/join "/" [rest-url index "_search"]))

(defn- make-query [q] {"query" {"bool" {"must" {"multi_match" {"query" q
                                                               "fields" ["title" "director" "yearstring"]}}}}})

(defn search! [query from size]
  (print (json/generate-string query))
  (let [url          (search-url options)
        request-opts {:url          url
                      :method       :get
                      :timeout      http-request-timeout-ms
                      :body         (json/generate-string (make-query query))
                      :headers      {"Content-Type" "application/json"}
                      :query-params {:from from :size size}}
        {:keys [body status error]} @(request request-opts)]

    (if (not= status 200)
      (throw (ex-info "Search request failed" {:request request-opts
                                               :status status
                                               :error error
                                               :body body}))
      (json/parse-string body))))
