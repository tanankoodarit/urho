(ns urho.utils.generator)

(defn- round
  "Round a double to the given precision (number of significant digits)"
  [precision d]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))

(defn generateLocation [min max]
  (round 3 (+ (rand (- max min)) min)))


