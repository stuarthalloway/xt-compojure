(ns relevance.string-template)

(defn render-template [template-path attributes]
  (let [template (org.antlr.stringtemplate.StringTemplate.
                  (slurp (str "templates/" template-path)))]
    (.toString template)))