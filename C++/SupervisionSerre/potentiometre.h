#ifndef POTENTIOMETRE_H
#define POTENTIOMETRE_H
#include "capteur.h"
#include <stdlib.h>
#include <iostream>
#include <Windows.h>

class Potentiometre : public Capteur
{
private:
    int direction;
    int val_capt;
public:
    Potentiometre();
    void mesurer();
    int get_direction(int d);

};

#endif // POTENTIOMETRE_H
