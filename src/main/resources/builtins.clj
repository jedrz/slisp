(defn empty? [lst]
  (= (count lst) 0))

(defn cons [element lst]
  (concat (list element) lst))

(defn conj [lst element]
  (concat lst (list element)))

(defn nth [lst index]
  (if (empty? lst)
    nil
    (if (> index 0)
      (nth (rest lst) (- index 1))
      (first lst))))

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

(defn and [arg & rst]
  (if (empty? rst)
    arg
    (if arg
      (apply and (first rst) (rest rst))
      false)))

(defn or [arg & rst]
  (if (empty? rst)
    arg
    (if arg
      arg
      (apply or (first rst) (rest rst)))))

(defmacro when [cond & body]
  (list 'if cond (cons 'do body) nil))

(defn zero? [n]
  (= n 0))

(defn char-at [s pos]
  (subs s pos (+ pos 1)))

(defmacro println [args]
  (list 'apply 'print args "\n"))

(defn sort [comp lst]
  (if (empty? lst)
    '()
    (let [pivot (first lst)
          lower (filter (fn [n]
                          (comp n pivot))
                        (rest lst))
          not-lower (filter (fn [n]
                              (not (comp n pivot)))
                            (rest lst))]
      (concat (sort comp lower)
              (list pivot)
              (sort comp not-lower)))))
