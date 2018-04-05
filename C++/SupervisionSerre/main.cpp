#include <QCoreApplication>
#include "pluviometre.h"
#include "potentiometre.h"
#include "capteur.h"
#include "anemometre.h"

int main()
{
    while (true)
    {
        Potentiometre poten;
        poten.mesurer();

        Anemometre anemo;
        anemo.mesurer();

    }






    return 0;
}
