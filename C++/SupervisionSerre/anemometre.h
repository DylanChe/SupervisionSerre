#ifndef ANEMOMETRE_H
#define ANEMOMETRE_H
#include "capteur.h"
#include <stdlib.h>
#include <iostream>
#include <Windows.h>
class Anemometre : public Capteur
{
private:
    int rotations;
    float vitesse;
public:
    Anemometre();
    void mesurer();
    int get_vitesse(int v);
};

#endif // ANEMOMETRE_H
