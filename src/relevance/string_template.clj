(ns relevance.string-template
  (:use clojure.contrib.java-utils))

(def *template-group* (org.antlr.stringtemplate.StringTemplateGroup. "templates"))

(defn render-template [template-path attributes]
  (let [template (.getInstanceOf *template-group* template-path)]
    (doseq [[k v] attributes]
      (.setAttribute template (as-str k) v))
    (.toString template)))