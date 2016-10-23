(ns urho.routes.health-route
  (:require [urho.properties :refer [current-version]]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.util.http-response :refer [ok]]))


(s/defschema Ping {:version s/Str :status s/Str})

(defn- create-ping-response []
  {:version current-version :status "OK"})


(defroutes health-route
           (GET "/v1/health" request
                (ok (create-ping-response)))
           )

