(ns statusfy.core
  (:require [ring.adapter.jetty :as jetty]))

(defn -main [port]
  (jetty/run-jetty (fn [req]
                     {:status 200
                      :body "Statusfy!"
                      :headers {}})
                   {:port (Integer. port)}))