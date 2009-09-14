(use 'compojure
     'clj-calendar.main)

(run-server {:port 8080} "/*" (servlet clj-calendar-server))