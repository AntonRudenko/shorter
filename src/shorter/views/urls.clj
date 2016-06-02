(ns shorter.views.urls
  (:require [shorter.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]
            [clojure.string :as str]))

(defn url-form []
  [:div {:id "url-form" :class ""}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 [:div {:class "form-group"}
                  (form/label "url" "enter you url")
                  (form/text-field {:class "form-control"} "url")]
                 (form/submit-button {:class "btn btn-default"} "save"))])

(defn display-urls [urls]
  [:table {:class "table"}
   [:thead
    [:tr
     [:th "id"]
     [:th "url"]
     [:th "creation date"]]]
   [:tbody
    (map
      (fn [url]
        [:tr
         [:td (:id url)]
         [:td (:url url)]
         [:td (:date_created url)]]
        )
      urls)]
   ])

(defn- show-message [message]
  [:div {:class "alert alert-info"} (or message "")])

(defn index [message]
  (layout/common "make you url real short"
                 [:div {:class "jumbotron"}
                  [:h1 "Make you url short"]]
                 (when-not (str/blank? message)
                   (show-message message))
                 (url-form)))

(defn list [urls]
  (layout/common "Urls"
                 (display-urls urls)))

(defn created [url host]
  (layout/common "Url encoded"
                 [:div {:class "jumbotron"}
                  [:h1 "You encoded url is: "]
                  [:p [:a {:href (str host "/" url)} (str host "/" url)]]
                  ]))



