(ns clj-calendar.main-test
  (:use clojure.test clj-calendar.main clojure.contrib.lazy-xml)
  (:require [clojure.xml :as xml]))

(defn string->xml [str]
  (xml/parse (org.xml.sax.InputSource. (java.io.StringReader. str))))

(defn string->lazyxml [str]
  (parse-trim (java.io.StringReader. str)))

(deftest test-html->compojure
  (doseq [[result input]
           (partition 2 [
                         [:h1 "hello"]
                         "<h1>hello</h1>"
       
                         [:h1 {:class "friendly"} "howdy"]
                         "<h1 class='friendly'>howdy</h1>"

                         [:div {:id "chapter"} [:p "And so..."]]
                         "<div id='chapter'><p>And so...</p></div>"

                         [:div [:p "one"] [:p "two"]]
                         "<div><p>one</p><p>two</p></div>"

                         [:div "mixed" [:span "content"] "ftw"]
                         "<div>mixed<span>content</span>ftw</div>"])]
    (is (= result (html->clj (string->xml input))))
    (is (= result (html->clj (string->lazyxml input))))))