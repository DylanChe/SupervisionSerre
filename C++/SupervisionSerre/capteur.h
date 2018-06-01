#ifndef CAPTEURS_H
#define CAPTEURS_H
#include <iostream>
#include <Windows.h>
class Capteur
{
public:
    Capteur();
    virtual void mesurer() = 0;
};

#endif // CAPTEURS_H
