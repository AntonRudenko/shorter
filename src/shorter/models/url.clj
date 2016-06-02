(ns shorter.models.url
  (:require [clojure.java.jdbc :as sql]))

(def spec {:subprotocol "postgresql"
           :subname "//127.0.0.1:5432/shorter"
           :user "postgres"
           :password "postgres"})

(defn all []
  (into [] (sql/query spec ["select * from url order by date_created desc"])))

(defn find-by-url [url]
  (nth
    (sql/query spec ["select * from url where url = ?" url])
    0 nil))

(defn create [url]
  (nth (sql/insert! spec :url {:url url}) 0))

(defn get-or-create [url]
  (or (find-by-url url)
      (create url)))

(defn find-by-id [id]
  (nth
    (sql/query spec ["select * from url where id = ?" id])
    0 nil))

