#!/bin/sh

latexmk -latexoption="-shell-escape" -pdf -pvc doc.tex
