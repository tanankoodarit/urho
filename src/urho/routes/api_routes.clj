(ns urho.routes.api-routes
  (:require [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.util.http-response :refer [ok]]
            [urho.utils.generator :refer [generateLocation]]))



(defn- gen [count]
  (for [n (range count)]
    {:name "Generated point"
     :lat  (generateLocation 40.000 60.000)
     :lon  (generateLocation 20.000 31.000)}
    )
  )


(defroutes api-routes
           (GET "/v1/points" []
                :query-params [count :- Long]
                (ok (gen (int count)))))


