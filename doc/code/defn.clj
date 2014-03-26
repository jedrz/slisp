(defn sum [a b]
  "Dodaje dwie liczby"
  (+ a b))

(defn avg [& lst]
  (/ (apply + lst) (count lst)))
