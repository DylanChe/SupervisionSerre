#ifndef PLUVIOMETRE_H
#define PLUVIOMETRE_H
#include "capteur.h"
#include <stdlib.h>
#include <iostream>
#include <Windows.h>
class Pluviometre : public Capteur
{
private:
    int pluie;

public:
    Pluviometre();
    void mesurer();
};

#endif // PLUVIOMETRE_H
