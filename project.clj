(defproject clj-parrot "0.1.0"
  :description "curlable party parrot in clojure"
  :url "https://github.com/taso42/clj-parrot"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring/ring-defaults "0.3.1"]]
  :main ^:skip-aot clj-parrot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
