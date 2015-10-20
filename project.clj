(defproject statusfy "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
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
