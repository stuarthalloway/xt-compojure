(ns clj-calendar.main
  (:use compojure relevance.string-template))

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
  (GET "/demos/stringtemplate/hello"
       (render-template "hello" {}))
  (GET "/demos/stringtemplate/simple"
       (render-template "simple" {:name "World"}))
  (GET "/demos/stringtemplate/expressions"
       (render-template "expressions" [ [:numbers (range 5)]
                                        [:user {"fname" "Stu" "lname" "Halloway"}]]))
  (ANY "*" (page-not-found)))

