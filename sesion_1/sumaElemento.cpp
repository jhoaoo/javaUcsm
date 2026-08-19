#include <iostream>
#include <vector>

int sumarElementos(const std::vector<int>& arreglo) {
    int suma = 0;
    for (int numero : arreglo) {
        suma += numero;
    }
    return suma;
}

int main() {
    std::vector<int> numeros = {1, 2, 3, 4, 5};
    int resultado = sumarElementos(numeros);
    std::cout << "La suma de los elementos del arreglo es: " << resultado << std::endl;
    return 0;
}
