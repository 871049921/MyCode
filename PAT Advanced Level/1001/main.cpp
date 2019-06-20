#include <stdio.h>
#include <string.h>

int main() {
    char num[][10] = {"ling", "yi", "er", "san", "si", "wu", "liu", "qi", "ba", "jiu"};
    char str[100];
    int flag = 0;
    int bit = 0;
    scanf("%s", str);
    if(str[0] == '-' ) {
        flag = 1;
        printf("Fu ");
    }
    for(int i = flag; str[i] != '\0'; i ++) {
        bit ++;
    }
    switch(bit) {
        case 1:
            printf("%s", num[str[flag] - 48]);
            break;
        case 2:
            printf("%s Shi %s", num[str[flag] - 48], num[str[flag + 1] - 48]);
            break;
        case 3:
            if(str[flag + 1] - 48 == 0 && str[flag + 2] - 48 == 0) {
                printf("yi Bai");
            } else if(str[flag + 1] - 48 == 0 && str[flag + 2] - 48 != 0) {
                printf("%s Bai ling %s", num[str[flag] - 48], num[str[flag + 2] - 48]);
            } else if(str[flag + 1] - 48 != 0 && str[flag + 2] - 48 == 0) {
                printf("%s Bai %s Shi", num[str[flag] - 48], num[str[flag + 1] - 48]);
            } else if(str[flag + 1] - 48 != 0 && str[flag + 2] - 48 != 0) {
                printf("%s Bai %s Shi %s", num[str[flag] - 48], num[str[flag + 1] - 48], num[str[flag + 2] - 48]);
            }
            break;
        case 4:
            break;
    }
}