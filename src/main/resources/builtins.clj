(defn empty? [lst]
  (= (count lst) 0))

(defn cons [element lst]
  (concat (list element) lst))

(defn conj [lst element]
  (concat lst (list element)))

(defn zero? [n]
  (= n 0))