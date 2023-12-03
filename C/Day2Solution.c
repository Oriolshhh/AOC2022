#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    FILE* f; 
    
    f = fopen("PuzzleDay2", "r"); 
    
    int total = 0;
    char line[100];
    while(!feof(f)) {
        fgets(line , 500, f);
        printf("%s\n", line); 
        
        switch(line[0]) {
            case 'A': //rock
                switch(line[2]) {
                    case 'X': //lose
                    total = total + 3;
                        break; 
                    case 'Y': //draw
                    total = total + 1;
                    total = total + 3; 
                        break; 
                    case 'Z': //win
                    total = total + 2;
                    total = total + 6;
                        break; 
                }
                break; 
            case 'B': //paper
                switch(line[2]) {
                    case 'X': //lose
                    total = total + 1;
                        break; 
                    case 'Y': //draw
                    total = total + 2;
                    total = total + 3;
                        break; 
                    case 'Z': //win
                    total = total + 3;
                    total = total + 6;
                        break; 
                }
                break; 
            case 'C': //scissors
                switch(line[2]) {
                    case 'X': //lose
                    total = total + 2;
                        break; 
                    case 'Y': //draw
                    total = total + 3;
                    total = total + 3;
                        break; 
                    case 'Z': //win
                    total = total + 1;
                    total = total + 6; 
                        break; 
                }
                break; 
        }
        
    }
    
    printf("%d", total); 
    return 0;
}
