(ns shorter.util.hashids
  (require [hashids.core :as hash]))

(def hashids-opts {:salt (or (System/getenv "SHORTENER_SALT") "a2bGS217A")})
;
(defn encode [id]
   (hash/encode hashids-opts id))
;
(defn decode [str]
  (let [result (hash/decode hashids-opts str)]
    (if (not-empty result)
      (nth result 0)
      nil)))
;

(require '[clojure.java.jdbc :as sql])