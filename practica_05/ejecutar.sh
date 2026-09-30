#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
mkdir -p out
javac -encoding UTF-8 -Xlint:all -Werror -d out @sources.txt
for clase in actividades.ImprimirArreglo actividades.PruebaPila actividades.IgualGenerico actividades.PruebaIgualdadPilas propuestos.PruebaAccesoresPar propuestos.PruebaPar propuestos.Main propuestos.PruebaContenedor cuestionario.EjemplosGenericos Verificacion
do
    java -cp out "$clase"
done
