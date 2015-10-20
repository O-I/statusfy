(ns statusfy.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]))

(defn status [{:keys [uri] :as req}]
  {:status 200
   :body "Statusfy is up!"
   :headers {}})

(defroutes app
  (GET "/" [] status)
  (not-found "Can't get no Statusfaction"))

(defn -main [port]
  (jetty/run-jetty status
                   {:port (Integer. port)}))

(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port)}))