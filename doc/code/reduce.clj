(reduce (fn [acc n]
          (+ acc (* n n)))
        '(1 2 3)) => 14
(reduce (fn [acc n]
          (+ acc (* n n)))
        '(1 2 3)
        1) => 15
