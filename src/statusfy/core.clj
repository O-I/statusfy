(ns statusfy.core
  (:require [ring.adapter.jetty :as jetty]))

(defn status [req]
  {:status 200
   :body "Statusfy is up!"
   :headers {}})

(defn -main [port]
  (jetty/run-jetty status
                   {:port (Integer. port)}))