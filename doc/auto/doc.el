(TeX-add-style-hook "doc"
 (lambda ()
    (TeX-add-symbols
     '("clj" 1))
    (TeX-run-style-hooks
     "minted"
     "longtable"
     "float"
     "xcolor"
     "usenames"
     "color"
     "amsmath"
     "hyperref"
     "graphicx"
     "pdftex"
     "indentfirst"
     ""
     "inputenc"
     "utf8"
     "polski"
     "MeX"
     "latex2e"
     "art11"
     "article"
     "11pt"
     "a4paper")))

