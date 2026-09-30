@echo off
chcp 65001 >nul
cd /d "%~dp0"
if not exist out mkdir out
javac -encoding UTF-8 -Xlint:all -Werror -d out @sources.txt
if errorlevel 1 exit /b 1
for %%C in (actividades.ImprimirArreglo actividades.PruebaPila actividades.IgualGenerico actividades.PruebaIgualdadPilas propuestos.PruebaAccesoresPar propuestos.PruebaPar propuestos.Main propuestos.PruebaContenedor cuestionario.EjemplosGenericos Verificacion) do (
 java -cp out %%C
 if errorlevel 1 exit /b 1
)
