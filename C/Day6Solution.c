#include <stdio.h>
#include <string.h>

int countDistinct(char temp[], int n) {
    int totalDistinct = 1;
    
    //for all the characters of the String
    for (int i = 1; i < n; i++) {
        int j = 0;
        
        //check character i 
        for (j = 0; j < i; j++) {
            if (temp[i] == temp[j]) {
                break; //if they are equals stop
            }
        }        
        if (i == j)
            totalDistinct++; //increment the total distinc characters
    }
    
    return totalDistinct;
}

int solution(char line[], int limit) {
    char temp[20];
    int solution = 0;
    
    //for all the characters of the file
    for (int i = 0; i < strlen(line); i++) {
        //get the limit characters of the consecute character on temp String 
        for (int j = 0; j < limit; j++) {
            temp[j] = line[i + j]; 
        }
        //if the totalDistinct characters of that temp String equals the number of characters that we want (limit)
        if (countDistinct(temp, strlen(temp)) == limit) {
            solution = i + limit; //return the charactet where we were + the number of characters that we had to check (limit)
            return solution; //return the solution
        }
    }
}

int main() {
    FILE* f; 
    char line[10000];

    f = fopen("puzzle6", "r"); 
    
    //read file
    fgets(line, 10000, f); 
    line[strlen(line) - 1] = '\0'; 

    printf("SOLUTION TO PUZZLE 1: %d\n", solution(line, 4)); 
    printf("SOLUTION TO PUZZLE 2: %d", solution(line, 14)); 
    
    return 0; 
}
