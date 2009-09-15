(ns clj-calendar.html-compojure-test
  (:use clojure.test clj-calendar.html-compojure))

(defn check-html->compojure [inputs]
  (doseq [[result input]
          (partition 2 inputs)]
    (is (= result (html->compojure input)))))

(deftest test-html->compojure
  (binding [*trim* true]
    (check-html->compojure [
                            [:h1 "hello"]
                            "<h1>hello</h1>"
       
                            [:h1 {:class "friendly"} "howdy"]
                            "<h1 class='friendly'>howdy</h1>"

                            [:div {:id "chapter"} [:p "And so..."]]
                            "<div id='chapter'><p>And so...</p></div>"

                            [:div [:p "one"] [:p "two"]]
                            "<div><p>one</p><p>two</p></div>"
                         
                            [:div "foo"]
                            "<div>   foo\n  </div>"
                         
                            [:div "mixed" [:span "content"] "ftw"]
                            "<div>mixed<span>content</span>ftw</div>"]))
  (binding [*trim* false]
    (check-html->compojure [
                            [:h1 "hello"]
                            "<h1>hello</h1>"
                            
                            [:h1 {:class "friendly"} "howdy"]
                            "<h1 class='friendly'>howdy</h1>"
                            
                            [:div {:id "chapter"} [:p "And so..."]]
                            "<div id='chapter'><p>And so...</p></div>"
                            
                            [:div [:p "one"] [:p "two"]]
                            "<div><p>one</p><p>two</p></div>"
                            
                            [:div "   foo\n  "]
                            "<div>   foo\n  </div>"
                            
                            [:div "mixed" [:span "content"] "ftw"]
                            "<div>mixed<span>content</span>ftw</div>"])))