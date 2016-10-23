(defproject urho "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0-alpha13"]
                 [compojure "1.5.1"]
                 [metosin/compojure-api "1.1.8"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-devel "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring.middleware.jsonp "0.1.6"]
                 [ring-cors "0.1.8"]
                 [http-kit "2.2.0"]
                 [environ "1.1.0"]
                 [ring-json-response "0.2.0"]
                 [com.fasterxml.jackson.core/jackson-core "2.8.0.rc1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [ch.qos.logback/logback-core "1.1.3"]
                 [ch.qos.logback/logback-classic "1.1.3"]
                 [net.logstash.logback/logstash-logback-encoder "4.6"]
                 [org.clojure/tools.trace "0.7.8"]
                 [org.clojure/clojure "1.9.0-alpha13"]
                 ]
  :ring {:handler urho.routes.handler/all-routes
         :port    8090
         :reload-paths ["src/"]}
  :resource-paths ["resources/"]
  :source-paths ["src/"]
  :uberjar-name "server.jar"
  :profiles
  {:dev {:env          {:environment "dev"}
         :dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]
                        [http-kit.fake "0.2.2"]
                        [clj-containment-matchers "1.0.1"]
                        [ring-mock "0.1.5"]
                        [clj-stacktrace "0.2.8"]]}}
  :repl-options {:caught clj-stacktrace.repl/pst+}
  :plugins [[lein-cljfmt "0.3.0"]
            [lein-ancient "0.6.8"]
            [lein-deps-tree "0.1.2"]
            [lein-ring "0.9.7"]]
  :aot [clojure.tools.logging.impl
        clj-time.core
        urho.main]
  :cljfmt {:file-pattern #".*\.clj$"}
  :main urho.main
  )
