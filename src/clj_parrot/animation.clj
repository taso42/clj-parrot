(ns clj-parrot.animation
  (:require [clojure.java.io :as io]
            [ring.util.io :as ring-io]))

(def parrot-frames
  (map #(slurp (io/resource (str "frames/" % ".txt"))) (range 0 10)))

(defn clear
  [out]
  (.write out (.getBytes "\033[2J(\033[H")))

(def colors [31 32 33 34 35 36 37])
(defn set-rand-color
  [out]
  (let [rand       (rand-int (count colors))
        color-code (nth colors rand)]
    (.write out (.getBytes (format "\033[%dm" color-code)))))

(defn animate
  [out]
  (dorun (map (fn [parrot]
                (clear out)
                (set-rand-color out)
                (.write out (.getBytes parrot))
                (.flush out)
                (Thread/sleep 60))
              parrot-frames)))

(defn animation-stream
  []
  (ring-io/piped-input-stream (fn [out]
                                (try
                                  (dorun (repeatedly (fn [] (animate out))))
                                  (finally
                                    (.close out))))))
