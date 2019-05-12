// À≥–Ú’ª
#ifndef STACK_H
#define STACK_H

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define OVERFLOW -1
#define INFEASIBLE -2
#define STACK_INIT_SIZE 100
#define STACKINCREMENT 10

typedef int Status;

typedef struct {
    SElemType *base;
    SElemType *top;
    int stacksize;
} SqStack;

Status InitStack(SqStack *);// ≥ı ºªØ’ª
Status DestoryStack(SqStack *);// œ˙ªŸ’ª
Status StackEmpty(SqStack);// ’ª «∑ÒŒ™ø’
Status GetTop(SqStack, SElemType *);// ªÒ»°’ª∂•‘™Àÿ
Status Push(SqStack *, SElemType);// »Î’ª
Status Pop(SqStack *, SElemType *);// ≥ˆ’ª
Status StackTraverse(SqStack);// ±È¿˙’ª

Status InitStack(SqStack *s) {
    s->base = (SElemType *)malloc(sizeof(SElemType) * STACK_INIT_SIZE);
    if(!s->base) {
        exit(OVERFLOW);
    }
    s->top = s->base;
    s->stacksize = STACK_INIT_SIZE;
}

Status GetTop(SqStack s, SElemType *e) {
    if(s.top == s.base) {
        return ERROR;
    }
    *e = *(s.top - 1);
    return OK;
}

Status Push(SqStack *s, SElemType e) {
    if(s->top - s->base >= s->stacksize) {
        s->base = (SElemType *) realloc(s->base, (s->stacksize + STACKINCREMENT) * sizeof(SElemType));
        if (!s->base) {
            exit(OVERFLOW);
        }
        s->top = s->base + s->stacksize;
        s->stacksize += STACKINCREMENT;
    }
    *s->top++ = e;
    return OK;
}

Status Pop(SqStack *s, SElemType *e) {
    if(s->top == s->base) {
        return ERROR;
    }
    *e = *--s->top;
    return OK;
}

Status DestoryStack(SqStack *s) {
    if(s->base) {
        free(s->base);
    }
    s->top = s->base = NULL;
    return OK;
}

Status StackEmpty(SqStack s) {
    if(s.top == s.base) {
        return TRUE;
    } else {
        return FALSE;
    }
}

Status StackTraverse(SqStack s) {
    SElemType *p = s.base;
    int i = 0;
    if(s.base == s.top) {
        printf("∂—’ª“—ø’!\n");
        return OK;
    }
    while(p < s.top) {
        printf("[%d:%d]", ++i, *p++);
    }
    printf("\n");
    return OK;
}

#endif //STACK_H
