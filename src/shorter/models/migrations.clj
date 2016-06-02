(ns shorter.models.migrations
  (require [clojure.java.jdbc :as sql]
           [shorter.models.url :as url]))

(defn migrated? []
  (-> (sql/query url/spec
                 ["select count(*) from information_schema.tables where table_name='url'"]))
  first :count pos?)

(defn migrate []
  (when (not (migrated?))
    (print "Creating tables") (flush)
    (sql/db-do-commands url/spec
                        (sql/create-table-ddl
                          :url
                          [:id :serial "PRIMARY KEY"]
                          [:url :varchar "NOT NULL"]
                          [:date_created :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println "done")))