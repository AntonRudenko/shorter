(ns shorter.views.layout
  (require [hiccup.page :as h]))

(defn common [title & body]
  (h/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
     [:meta {:name "viewport" :content
                   "width=device-width, initial-scale=1, maximum-scale=1"}]
     [:title title]
     (h/include-css "/bootstrap/css/bootstrap.min.css")
     (h/include-css "/bootstrap/css/bootstrap-theme.min.css")
     (h/include-js "/bootstrap/js/bootstrap.min.js")]
    [:body
     [:div {:class "container"} body]]))

(defn not-found []
  (common "Page not found "
          "Page can't be found"))