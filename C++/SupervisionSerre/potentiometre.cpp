#include "potentiometre.h"
Potentiometre::Potentiometre()
{
    direction = 0;
    val_capt = 0;

}

int Potentiometre::get_direction(int d)
{
    if (d < 22)
    {
        std::cout<<"Nord"<<std::endl;
    }
    else if (d < 67)
    {
        std::cout<<"Nord Est"<<std::endl;
    }
    else if (d < 112)
    {
        std::cout<<"Est"<<std::endl;
    }
    else if (d < 157)
    {
        std::cout<<"Sud Est"<<std::endl;
    }
    else if (d < 212)
    {
        std::cout<<"Sud"<<std::endl;
    }
    else if (d < 292)
    {
        std::cout<<"Sud Ouest"<<std::endl;
    }
    else if (d < 337)
    {
        std::cout<<"Ouest"<<std::endl;
    }
    else
    {
        std::cout<<"Nord"<<std::endl;
    }



}

void Potentiometre::mesurer()
{



    //val_capt = rand () %1024;
    direction = rand() % 360;

    if (direction > 360)
    {
        direction = direction - 360;
    }
    if (direction < 0)
    {
        direction = direction + 360;
    }
    std::cout<<direction<<std::endl;
    get_direction(direction);
    Sleep(3000);








}

