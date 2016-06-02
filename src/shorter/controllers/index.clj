(ns shorter.controllers.index
  (require [compojure.core :refer [defroutes GET POST]]
           [clojure.string :as str]
           [ring.util.response :as ring]
           [shorter.views.urls :as view]
           [shorter.models.url :as model]
           [shorter.util.hashids :as hash]
           [shorter.views.layout :as layout])
  (:import (org.apache.commons.validator.routines UrlValidator)))

(defn index []
  (view/index ""))

(defn- valid-url? [url-str]
  (let [validator (UrlValidator.)]
    (.isValid validator url-str)))

(defn encode [url host]
  (if-not (str/blank? url)
    (if (valid-url? url)
      (-> (:id (model/get-or-create url))
          (hash/encode)
          (view/created host))
      (view/index "url is malformed"))
    (view/index "url is empty")))

(defn decode [hash]
  (let [id (hash/decode hash)]
    (println id)
    (if-not (nil? id)
      (let [urlModel (model/find-by-id id)]
        (when-not (nil? urlModel)
          (ring/redirect (:url urlModel))))
      (layout/not-found))))


(defroutes routes
           (GET "/" [] (index))
           (POST "/" [url :as {headers :headers}]
             (encode url (str "http://"
                              (get headers "host"))))
           (GET "/:hash" [hash] (decode hash)))


