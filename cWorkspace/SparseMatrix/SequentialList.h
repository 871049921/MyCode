
#ifndef SEQUENTIALLIST_H
#define SEQUENTIALLIST_H

#include <stdio.h>
#include <stdlib.h>

typedef int Status;

typedef struct {
    ElemType *elem;
    int length;
    int listsize;
} SqList;

#endif //SEQUENTIALLIST_H
