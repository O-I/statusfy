(ns statusfy.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]))

(defn status []
  {:status 200
   :body "Statusfy is up!"
   :headers {}})

(defn body [code]
  (case code
    200 "OK"))

(defn status-code [{:keys [:route-params]}]
  (let [code (-> route-params :code Integer.)]
    {:status code
     :body (body code)
     :headers {}}))

(defroutes app
  (GET "/:code" [] status-code)
  (GET "/status" [] status)
  (GET "/request" [] handle-dump)
  (not-found "Can't get no Statusfaction"))

(defn -main [port]
  (jetty/run-jetty status
                   {:port (Integer. port)}))

(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port)}))