(defproject statusfy "0.1.0-SNAPSHOT"
  :description "A simple status code server"
  :url "https://statusfy.herokuapp.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.4.0"]
                 [compojure "1.4.0"]]
  :min-lein-version "2.0.0"
  :uberjar-name "statusfy.jar"
  :main statusfy.core
  :profiles {:dev
             {:main statusfy.core/-dev-main}})
