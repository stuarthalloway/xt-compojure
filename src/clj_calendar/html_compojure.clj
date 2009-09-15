(ns clj-calendar.html-compojure
  (:require [clojure.xml :as xml]))

(def *trim* true)

(defn cdata? [node*]
  (or (string? node*) (every? string? node*)))

(defmulti htmlseq->compojure #(cond
                      (and *trim* (cdata? %)) :trim-cdata
                      (cdata? %) :cdata
                      (sequential? %) :nodes
                      :else :node))

(defmethod htmlseq->compojure :nodes [nodes]
  (apply vector (map htmlseq->compojure nodes)))

(defmethod htmlseq->compojure :trim-cdata [node*]
  (if (string? node*)
    (.trim node*)
    (map #(.trim %) node*)))

(defmethod htmlseq->compojure :cdata [node] node)

(defmethod htmlseq->compojure :node [node]
  (vec
   (let [attrs (node :attrs)]
     (if (seq attrs)
       (concat [(node :tag) attrs]
               (htmlseq->compojure (node :content)))
       (cons (node :tag)
             (htmlseq->compojure (node :content)))))))

(defmulti html->compojure class)

(defmethod html->compojure String [str]
  (htmlseq->compojure (xml/parse (org.xml.sax.InputSource. (java.io.StringReader. str)))))