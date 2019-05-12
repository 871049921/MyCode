#include <stdio.h>
#include "Stack.h"

void Input(char *);// ÊäÈëÊý¾Ý
void DealString(char *);// ´¦Àí×Ö·û´®
int DealChar(char, char);// ´¦Àí×Ö·û

int main() {

    char string[100];
    Input(string);
    DealString(string);

    return 0;
}

void Input(char *string) {
    printf("ÊäÈë×Ö·û´®:\n");
    scanf("%s", string);
}

void DealString(char *string) {
    SqStack s;
    InitStack(&s);
    char e;

    for(int i = 0; string[i] != '\0'; i ++) {
        char ch = string[i];
        switch (ch) {
            case '(':
            case '[':
            case '{':
                Push(&s, ch);
                break;
            case ')':
            case ']':
            case '}':
                Pop(&s, &e);
                if(DealChar(ch, e) == FALSE) {
                    printf("À¨ºÅ²»Æ¥Åä£¡\n");
                    exit(0);
                }
                break;
        }
    }
    if(!StackEmpty(s)) {
        printf("À¨ºÅ²»Æ¥Åä£¡\n");
        exit(0);
    } else {
        printf("À¨ºÅÆ¥Åä£¡\n");
    }
}

int DealChar(char ch, char e) {
    char c;
    if(e == '(') {
        c = ')';
    } else if(e == '[') {
        c = ']';
    } else if(e == '{') {
        c = '}';
    }
    if(ch == c) {
        return TRUE;
    } else {
        return FALSE;
    }
}