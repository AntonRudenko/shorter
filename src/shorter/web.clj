(ns shorter.web
  (:require [compojure.core :refer [defroutes GET]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [shorter.controllers.index :as index]
            [shorter.views.layout :as layout]
            [shorter.models.migrations :as schema]))

(defroutes routes
           index/routes
           (route/resources "/")
           (route/not-found (layout/not-found)))

(def application (wrap-defaults routes site-defaults))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))