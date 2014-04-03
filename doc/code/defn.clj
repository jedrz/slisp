(defn sum [a b]
  (+ a b))

(defn avg [& lst]
  (/ (apply + lst) (count lst)))
