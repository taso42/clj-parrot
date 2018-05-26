(ns clj-parrot.core
  (:require [ring.middleware.defaults :as defaults]
            [ring.adapter.jetty :as jetty]
            [clj-parrot.animation :as anim])
  (:gen-class))

(def ^:dynamic *server*)

(defn ring-handler
  [request]
  (let [uri (:uri request)]
    (cond (= "/parrot" uri)
          {:status 200
           :body   (anim/animation-stream)}

          :else
          {:status 400
           :body "Bad request. Did you mean /parrot ?"})))

(defn start-server!
  [handler]
  (let [server (jetty/run-jetty handler {:port               3000
                                         :join?              false
                                         :output-buffer-size 8})]
    (alter-var-root #'*server* (constantly server))))

(defn stop-server!
  []
  (when *server*
    (.stop *server*)
    (alter-var-root #'*server* (constantly nil))))

(defn -main
  [& args]
  (start-server! ring-handler)
  (println "Ready for business"))
