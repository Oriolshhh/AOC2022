int main() {
    FILE* f; 
    
    char line[500];
    int n1_p1;
    int n2_p1;
    int n1_p2;
    int n2_p2;

    f = fopen("puzzle4", "r");

    int totalPairs = 0;
    int puzle2 = 0;
    fgets(line , 500, f);
    line[strlen(line) - 1] = '\0';
    
    while(!feof(f)) {

        sscanf(line, "%d-%d,%d-%d", &n1_p1, &n2_p1, &n1_p2, &n2_p2);

        if(n1_p1 <= n1_p2 && n2_p1 >= n2_p2) {
            totalPairs += 1;
        }
        
        else if(n1_p1 >= n1_p2 && n2_p1 <= n2_p2) {
            totalPairs += 1;
        }

        if ((n1_p1 >= n1_p2 && n1_p1 <= n2_p2) || (n1_p2 >= n1_p1 && n1_p2 <= n2_p1)){
            puzle2 += 1;
        }

        fgets(line , 500, f);
        line[strlen(line) - 1] = '\0';

    }
    
    printf("%d\n", totalPairs);
    printf("%d", puzle2);
    

    return 0;
}