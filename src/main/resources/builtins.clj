(defn empty? [lst]
  (= (count lst) 0))

(defn cons [element lst]
  (concat (list element) lst))

(defn conj [lst element]
  (concat lst (list element)))

(defn map [f lst]
  (if (empty? lst)
    '()
    (cons (f (first lst)) (map f (rest lst)))))

(defn zero? [n]
  (= n 0))
