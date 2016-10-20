(ns urho.error-routes
  (:require [compojure.api.sweet :refer :all]
            [compojure.route :refer [not-found]]
            [ring.util.http-response :as response]))

(defroutes error-routes
           (ANY
             (response/not-found "Resource not found.")))
