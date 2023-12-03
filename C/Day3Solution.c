#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    FILE* f; 
    
    f = fopen("PuzzleDay3", "r");

    char repetit; 
    char line1[500]; 
    char line2[500];
    char line3[500];
    int total = 0;
    char alphabet[500] = {'res','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','\0'}; 
    
    while(!feof(f)) {
        fgets(line1, 500, f);
        line1[strlen(line1) - 1] = '\0';
        fgets(line2 , 500, f);
        line2[strlen(line2) - 1] = '\0';
        fgets(line3 , 500, f);
        line3[strlen(line3) - 1] = '\0';
        printf("%s\n", line1); 
        printf("%s\n", line2); 
        printf("%s\n", line3); 

        int stop = 0; 
        for(int i = 0; i < strlen(line1) && !stop; i++) {
            for(int j = 0; j < strlen(line2) && !stop; j++) {
                      for(int z = 0; z < strlen(line3) && !stop; z++) {
                        if(line1[i] == line3[z] && line3[z] == line2[j]) {
                            repetit = line1[i];
                            stop = 1;
                        }
                      }
              
            } 
        }

        stop = 0; 
        for(int i = 1; i <= strlen(alphabet) && !stop; i++) {
           if(repetit == alphabet[i]) {
               total = total + i;
               stop = 1;
            }
        }
    }

  printf("%d", total); 
  return 0;