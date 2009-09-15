(use 'clojure.test)

(defmacro re-test [test-sym]
  `(do
     (require :reload-all '~test-sym)
     (clojure.contrib.test-is/run-tests '~test-sym)))

(def tests
     '[clj-calendar.main-test
       clj-calendar.html-compojure-test])

(def exit-code (atom 0))

(defn run-tests! [& args]
  "Runs all tests"
  (doseq [test tests] (require test))
  (let [test-results (apply merge-with + (map test-ns tests))]

    (if (or (< 0 (test-results :fail))
            (< 0 (test-results :error)))
      (reset! exit-code -1)))
  (if (= [:shutdown-agents] args)
    (shutdown-agents))
  (System/exit @exit-code))


