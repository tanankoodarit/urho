(ns urho.main
  (:gen-class)
  (:require [clojure.tools.trace :refer [trace]]
            [clojure.tools.logging :refer [info error]]
            [ring.adapter.jetty :as jetty]
            [urho.routes.handler :refer [all-routes]]
            [urho.properties :refer [current-env
                                     print-properties
                                     parse-property
                                     current-version]])
  (:import [java.lang Runtime Thread]))


(def ^:private servet-opts
  {:max-threads 100
   :port        (parse-property :port)
   :join?       false})

(defn- run []
  (info "APPLICATION VERSION: " current-version)
  (info "Current environment: " (current-env))
  (info "File encoding:" (System/getProperty "file.encoding"))

  (print-properties)
  (let [jetty-server (jetty/run-jetty all-routes servet-opts)
        stop-fn (fn []
                  (info "Stopping Jetty Server...")
                  (.stop jetty-server)
                  (info "Stopped Jetty Server")
                  )]
    (info "Urho started successfully")
    stop-fn))

(defn- add-shutdown-hook [stop-fn]
  (let [hook (fn []
               (info "Stopping Urho...")
               (stop-fn)
               (info "OVP Program Store stopped successfully"))]
    (-> (Runtime/getRuntime)
        (.addShutdownHook (Thread. hook)))))

(defn -main []
  (try
    (info "Starting Urho...")
    (let [stop-fn (run)]
      (add-shutdown-hook stop-fn))
    (catch Exception e
      (error e "Unexpected error in main")
      (System/exit 1))))