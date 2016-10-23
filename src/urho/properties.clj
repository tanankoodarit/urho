(ns urho.properties
  (:use [environ.core :only [env]]
        [clojure.tools.logging :only [info]])
  (:require [clojure.string :as str]))

(def properties
  {:base       {:port 8090                                  ; application port

                }

   :dev        {}

   ; travis
   :ci         {}

   ; staging environment
   :test       {}

   ; production environment
   :production {}})

;Note that Environ automatically lowercases keys, and replaces the characters "_" and "." with "-".
(defn- keyword-to-sysenv-variable [key]
  (str/replace (name key) #"\." "-"))

(defn current-env []
  (or (env :environment)
      "dev"))

(defn- get-value-from-properties [key]
  (let [base-properties (:base properties)
        env-properties ((keyword (current-env)) properties)]
    ((keyword key) (merge base-properties env-properties))))

(defn- numeric? [s]
  (and
    (not (nil? s))
    (number? (read-string s))))

(defn- get-value-from-env-variables [key]
  (let [env-key (keyword-to-sysenv-variable key)
        env-value (env (keyword env-key))]
    (if (numeric? env-value)
      (read-string env-value)
      env-value)))

(defn- get-env-value [key]
  (or (get-value-from-env-variables key)
      (get-value-from-properties key)))

(defn- property [key]
  (let [value (get-env-value key)]
    (if (nil? value)
      (throw (Exception. (str "Value for " key " not given as environment parameter or found from properties file")))
      value)))

(def ^:private memoized-property
  (memoize property))

(defn parse-property [key]
  (memoized-property key))

(defn print-properties []
  (let [env-properties (properties (keyword (current-env)))]
    (doseq [[key _] env-properties]
      (info key "=" (property key)))))

(def current-version
  (let [version (or (get-env-value :package.version)
                    (:ovp-program-store-version env))]
    (str (current-env) " - " version)))
