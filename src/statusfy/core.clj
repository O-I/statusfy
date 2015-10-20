(ns statusfy.core
  (:require [ring.adapter.jetty :as jetty]))

(defn status [{:keys [uri] :as req}]
  (if (= uri "/")
    {:status 200
     :body "Statusfy is up!"
     :headers {}}
    {:status 404
     :body "Can't get no Statusfaction"
     :headers {}}))

(defn -main [port]
  (jetty/run-jetty status
                   {:port (Integer. port)}))