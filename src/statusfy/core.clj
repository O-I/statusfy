(ns statusfy.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]))

(defn status [req]
  {:status 200
   :body "Statusfy is up!"
   :headers {}})

(defn body [code]
  (get
   {100 "Continue"
    101 "Switching Protocols"
    102 "Processing"
    200 "OK"
    201 "Created"
    202 "Accepted"
    203 "Non-Authoritative Information"
    204 "No Content"
    205 "Reset Content"
    206 "Partial Content"
    300 "Multiple Choices"
    301 "Moved Permanently"
    302 "Found"
    303 "See Other"
    304 "Not Modified"
    305 "Use Proxy"
    306 "Unused"
    307 "Temporary Redirect"
    308 "Permanent Redirect"
    400 "Bad Request"
    401 "Unauthorized"
    402 "Payment Required"
    403 "Forbidden"
    404 "Not Found"
    405 "Method Not Allowed" 
    406 "Not Acceptable"
    407 "Proxy Authentication Required"
    408 "Request Timeout"
    409 "Conflict"
    410 "Gone"
    411 "Length Required"
    412 "Precondition Required"
    413 "Request Entry Too Large"
    414 "Request-URI Too Long"
    415 "Unsupported Media Type"
    416 "Requested Range Not Satisfiable"
    417 "Expectation Failed"
    418 "I'm a teapot"
    422 "Unprocessable Entity"
    428 "Precondition Required"
    429 "Too Many Requests"
    431 "Request Header Fields Too Large"
    500 "Internal Server Error"
    501 "Not Implemented"
    502 "Bad Gateway"
    503 "Service Unavailable"
    504 "Gateway Timeout"
    505 "HTTP Version Not Supported"
    511 "Network Authentication Required"
    520 "Unknown Error"
    522 "Connection Timed Out"
    524 "A Timeout Occurred"
    598 "Unknown Network Read Timeout Error"
    599 "Unknown Network Connect Timeout Error"}
    code "Statusfy can't crack that code"))

(defn status-code [{:keys [:route-params]}]
  (let [code (-> route-params :code Integer.)]
    {:status code
     :body (str code " " (body code))
     :headers {}}))

(defroutes app
  (GET "/"        [] status)
  (GET "/status"  [] status)
  (GET "/request" [] handle-dump)
  (GET "/:code"   [] status-code)
  (not-found      "Can't get no Statusfaction"))

(defn -main [port]
  (jetty/run-jetty app
                   {:port (Integer. port)}))

(defn -dev-main [port]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port)}))