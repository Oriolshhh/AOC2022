#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    FILE* f; 

    f = fopen("puzle.txt", "r"); 
    
    int max1 = 0, max2 = 0, max3 = 0, total = 0;
    char line[500];
 
    while(!feof(f)) {
        fgets(line , 500, f); 
        
        int comprovant = atoi(line);  
        if(comprovant == 0) {
            printf("\n%d and %d and %d and %d\n", total, max1, max2, max3); 
            if(total > max1) {
                max3 = max2;
                max2 = max1; 
                max1 = total;
            }
            else if(total > max2 && total != max1) {
                max3 = max2; 
                max2 = total;
            }
            else if(total > max3 && total != max1 && total != max2) {
                max3 = total; 
            }
            total = 0; 
        }
        else{
            total = total + atoi(line);
        }
    }

    total = max1 + max2 + max3; 
    printf("\n%d because %d and %d and %d", total, max1, max2, max3);
    
    return 0;
}