// problem11.cc
// solution to problem 11, project euler

#include <fstream>
#include <string>
#include <vector>
#include <cctype>
#include <iostream>
#include <tuple>

const int MULT_SIZE = 4;

enum class Direction
{
    Horizontal,
    Vertical,
    DiagonalLeft,
    DiagonalRight
};

void parseLine( std::vector< std::vector<long>* > &data, std::string line );
void printData( std::vector< std::vector<long>* > &data );
std::tuple<Direction,long> checkHorizontal( std::vector< std::vector<long>* > data );
std::tuple<Direction, long> checkVertical( std::vector< std::vector<long>* > data );
std::tuple<Direction, long> checkDiagonalRight( std::vector< std::vector<long>* > data );
std::tuple<Direction, long> checkDiagonalLeft( std::vector< std::vector<long>* > data );

int main()
{
    std::ifstream file( "data.txt" );
    std::string line;
    std::vector< std::vector<long>* > data;
    if ( !file.is_open() )
    {
        std::cout << "Couldn't open file!" << std::endl;
        return 1;
    }

    while ( !file.eof() )
    {
        std::getline( file, line );
        parseLine( data, line );
    }

    printData( data );

    auto largest = checkHorizontal( data );
    std::cout << "---------------------------------------" << std::endl;

    auto result = checkVertical( data );
    std::cout << "---------------------------------------" << std::endl;

    if( std::get<1>( largest ) < std::get<1>( result ) )
    if( std::get<1>( largest ) < std::get<1>( result ) )
    {
        largest = result;
    }

    result = checkDiagonalRight( data );
    std::cout << "---------------------------------------" << std::endl;

    if( std::get<1>( largest ) < std::get<1>( result ) )
    {
        largest = result;
    }

    result = checkDiagonalLeft( data );
    std::cout << "---------------------------------------" << std::endl;

    if( std::get<1>( largest ) < std::get<1>( result ) )
    {
        largest = result;
    }
    
    std::cout << "final result = " << std::get<1>( largest ) << std::endl;

    return 0;
}

std::tuple<Direction,long> checkHorizontal( std::vector< std::vector<long>* > data )
{
    auto result = std::make_tuple( Direction::Horizontal, 0l );
    for ( auto row : data )
    {
        for ( int i = row->size() - MULT_SIZE; i >= 0; i-- )
        {
            long total = 1;
            for ( int j = 0; j < MULT_SIZE; j++ )
            {
                total *= (*row)[i+j];
            }

            std::cout << total << std::endl;

            if ( total > std::get<1>( result ) )
            {
                result = std::make_tuple( Direction::Horizontal, total );
            }
        }
    }
    return result;
}

std::tuple<Direction, long> checkVertical( std::vector< std::vector<long>* > data )
{
    auto result = std::make_tuple( Direction::Vertical, 0l );
    
    for ( int x = 0; x < data[0]->size(); x++) // data[0]->size() wont change size, but not the most stable solution
    {
        for ( int y = data.size() - MULT_SIZE; y >= 0; y-- ) 
        {
            long total = 1;
            for ( int i = 0; i < MULT_SIZE; i++ )
            {
                total *= (*data[y+i])[x];
            }

            std::cout << total << std::endl;

            if ( total > std::get<1>( result ) )
            {
                result = std::make_tuple( Direction::Horizontal, total );
            }
        }
    }
    return result;
}

std::tuple<Direction, long> checkDiagonalRight( std::vector< std::vector<long>* > data )
{
    auto result = std::make_tuple( Direction::DiagonalRight, 0l );
    
    for ( int x = data[0]->size() - MULT_SIZE; x >= 0; x-- )
    {
        for ( int y = data.size() - MULT_SIZE; y >= 0; y-- )
        {
            long total = 1;

            for ( int i = 0; i < MULT_SIZE; i++ )
            {
                total *= (*data[y+i])[x+i];   
            }

            std::cout << total << std::endl;

            if ( total > std::get<1>( result ) )
            {
                result = std::make_tuple( Direction::DiagonalRight, total );
            }
        }
    }
    return result;
}

std::tuple<Direction, long> checkDiagonalLeft( std::vector< std::vector<long>* > data )
{
    auto result = std::make_tuple( Direction::DiagonalLeft, 0l );
    
    for ( int x = MULT_SIZE - 1; x < data[0]->size(); x++ )
    {
        for ( int y = 0; y < data.size() - MULT_SIZE; y++ )
        {
            long total = 1;

            for ( int i = 0; i < MULT_SIZE; i++ )
            {
                total *= (*data[y+i])[x-i];   
            }

            std::cout << total << std::endl;

            if ( total > std::get<1>( result ) )
            {
                result = std::make_tuple( Direction::DiagonalRight, total );
            }
        }
    }
    return result;
}

void printData( std::vector< std::vector<long>* > &data )
{
    for ( auto row : data )
    {
        for ( auto elem : *row )
        {
            std::cout << elem << " | ";
        }
        std::cout << std::endl;
    }
}

void parseLine( std::vector< std::vector<long>* > &data, std::string line )
{
    std::string num;

    if ( line.size() <= 0 )
        return;

    auto *new_row = new std::vector<long>();
    for ( auto c : line )
    {
        if( isdigit( c ) )
        {
            num.push_back( c );
        }
        else
        {
            new_row->push_back( std::stoi( num ) );
            num.clear();
        }
    }

    new_row->push_back( std::stoi( num ) );

    data.push_back( new_row );
}
