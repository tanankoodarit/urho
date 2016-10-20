(ns urho.handler
  (:require [compojure.handler :as handler]
            [ring.middleware.json :as middle]
            [compojure.api.sweet :refer [defapi]]
            [compojure.route :refer [not-found]]
            [ring.middleware.jsonp :refer [wrap-json-with-padding]]
            [compojure.api.core :as compojure-core]
            [urho.api-routes :refer [api-routes]]
            [urho.health-route :refer [health-route]]
            [urho.error-routes :refer [error-routes]]
            ))

(defn- add-encoding-to-json-response
  [handler]
  (fn [request]
    (let [response (handler request)
          response-with-encoding (assoc-in response [:headers "Content-Type"] "application/json; charset=UTF-8")]
      response-with-encoding)))

(def enabled-middleware
  [handler/api
   wrap-json-with-padding
   [middle/wrap-json-body {:keywords? true}]
   add-encoding-to-json-response])

(defapi all-routes
        {:swagger {:ui "/", :spec "/swagger.json"}}
        (compojure-core/middleware
          enabled-middleware
          health-route
          api-routes
          error-routes))