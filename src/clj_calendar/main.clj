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

(defn cdata? [node*]
  (or (string? node*) (every? string? node*)))

(defmulti html->clj #(cond
                      (cdata? %) :cdata
                      (sequential? %) :nodes
                      :else :node))

(defmethod html->clj :nodes [nodes]
  (apply vector (map html->clj nodes)))

(defmethod html->clj :cdata [node] node)

(defmethod html->clj :node [node]
  (vec
   (let [attrs (node :attrs)]
     (if (seq attrs)
       (concat [(node :tag) attrs]
               (html->clj (node :content)))
       (cons (node :tag)
             (html->clj (node :content)))))))

