(ns clj-calendar.main
  (:use compojure))

(defn root []
  (html
   (doctype :html4)
   [:html
    [:head
     [:title "Calendar"]
    [:body
     [:div#container
      [:h1 "TBD: draw a calendar"]]]]]))

(defroutes clj-calendar-server
  (GET "/" (root))
  (ANY "*" (page-not-found)))
