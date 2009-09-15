(ns clj-calendar.main
  (:use compojure))

(defn calendar []
  [:table {:class "calendar" :border 0 :cellspacing 0 :cellpadding 0}])

(defn root []
  (html
   (doctype :html4)
   [:html
    [:head
     [:title "Calendar"]
    [:body
     [:div#container
      [:h1 "TBD: draw a calendar"]
      (calendar)]]]]))

(defroutes clj-calendar-server
  (GET "/" (root))
  (ANY "*" (page-not-found)))

