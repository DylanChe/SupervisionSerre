#include "anemometre.h"

Anemometre::Anemometre()
{
    rotations = 0;
    vitesse = 0;


}


int Anemometre::get_vitesse(int v)
{
    if (v < 1)
    {
        std::cout<<"Vent Calme"<<std::endl;
    }
    else if (v < 5)
    {
        std::cout<<"Tres legere brise"<<std::endl;
    }
    else if (v < 11)
    {
        std::cout<<"Legere brise"<<std::endl;
    }
    else if (v < 19)
    {
        std::cout<<"Petite brise"<<std::endl;
    }
    else if (v < 28)
    {
        std::cout<<"Jolie brise"<<std::endl;
    }
    else if (v < 38)
    {
        std::cout<<"Bonne brise"<<std::endl;
    }
    else
    {
        std::cout<<"Bombe atomique"<<std::endl;
    }
}
void Anemometre::mesurer()
{


    rotations = rand() %10;
    vitesse = rotations * 1.2075;
    std::cout<<"La vitesse est de "<<vitesse<<"km/h"<<std::endl;
    get_vitesse(vitesse);
    Sleep(3000);






}
