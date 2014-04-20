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

(defn filter [f lst]
  (if (empty? lst)
    '()
    (if (f (first lst))
      (cons (first lst) (filter f (rest lst)))
      (filter f (rest lst)))))

(defn reduce [f lst acc]
  (if (empty? lst)
    acc
    (reduce f (rest lst) (f acc (first lst)))))

(defn not [arg]
  (if arg
    false
    true))

(defn zero? [n]
  (= n 0))
