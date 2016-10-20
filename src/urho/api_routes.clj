(ns urho.api-routes
  (:require [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.util.http-response :refer [ok]]))

(defroutes api-routes
           (GET "/v1/points" []
                (ok {:name "Moi"
                     :desc (str "The name you sent to me was ")})))
