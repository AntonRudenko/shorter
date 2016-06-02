(defproject shorter "0.1.0-SNAPSHOT"
  :description "Url shortener"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.6.0-rc2"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.5"]
                 [commons-validator/commons-validator "1.5.1"]
                 [clojurewerkz/urly "1.0.0"]]
  :main ^:skip-aot shorter.web
  :uberjar-name "shortener-standalone.jar"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler shorter.web/application
         :init shorter.models.migrations/migrate}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]
                                  [jstrutz/hashids "1.0.1"]]}
             :uberjar {:aot :all}})


