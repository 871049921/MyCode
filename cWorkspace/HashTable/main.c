#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define SUCCESS 1
#define UNSUCCESS 0
#define DUPLICATE -1
#define NULLKEY -1
#define OK 1

typedef int Status;

int hashsize[] = {997, 16};

typedef struct {
    int key;
} ElemType;

typedef int KeyType;

typedef struct {
    ElemType *elem;
    int count;
    int sizeindex;
} HashTable;

Status InitHash(HashTable *H);// 初始化
Status SearchHash1(HashTable H, KeyType K, int *p, int *c);// 哈希表搜索
Status InsertHash1(HashTable *H, ElemType e);// 哈希表插入
Status SearchHash2(HashTable H, KeyType K, int *p, int *c);// 哈希表搜索
Status InsertHash2(HashTable *H, ElemType e);// 哈希表插入
int Hash(KeyType K);// 哈希函数
void Collison1(int *p, int count);// 一次探测再散列
void Collison2(int *p, int count);// 二次探测再散列
void printHT(HashTable H);// 打印哈希表
void Input1(HashTable *H);// 输入值
void Input2(HashTable *H);// 输入值

int main() {
    HashTable H1, H2;
    InitHash(&H1);
    InitHash(&H2);
    Input1(&H1);
    Input2(&H2);
    printf("一次探测再散列\n");
    printHT(H1);
    printf("二次探测再散列\n");
    printHT(H2);
}

Status InitHash(HashTable *H) {
    H->sizeindex = 1;
    H->count = 0;
    H->elem = (ElemType*)malloc(hashsize[H->sizeindex] * sizeof(ElemType));
    for (int i = 0; i < hashsize[H->sizeindex]; i++) {
        H->elem[i].key = NULLKEY;
    }
    return OK;
}

void Input1(HashTable *H) {
    ElemType e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12;
    e1.key = 19;
    e2.key = 14;
    e3.key = 23;
    e4.key = 1;
    e5.key = 68;
    e6.key = 20;
    e7.key = 84;
    e8.key = 27;
    e9.key = 55;
    e10.key = 11;
    e11.key = 10;
    e12.key = 79;
    InsertHash1(H, e1);
    InsertHash1(H, e2);
    InsertHash1(H, e3);
    InsertHash1(H, e4);
    InsertHash1(H, e5);
    InsertHash1(H, e6);
    InsertHash1(H, e7);
    InsertHash1(H, e8);
    InsertHash1(H, e9);
    InsertHash1(H, e10);
    InsertHash1(H, e11);
    InsertHash1(H, e12);
}

void Input2(HashTable *H) {
    ElemType e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12;
    e1.key = 19;
    e2.key = 14;
    e3.key = 23;
    e4.key = 1;
    e5.key = 68;
    e6.key = 20;
    e7.key = 84;
    e8.key = 27;
    e9.key = 55;
    e10.key = 11;
    e11.key = 10;
    e12.key = 79;
    InsertHash2(H, e1);
    InsertHash2(H, e2);
    InsertHash2(H, e3);
    InsertHash2(H, e4);
    InsertHash2(H, e5);
    InsertHash2(H, e6);
    InsertHash2(H, e7);
    InsertHash2(H, e8);
    InsertHash2(H, e9);
    InsertHash2(H, e10);
    InsertHash2(H, e11);
    InsertHash2(H, e12);
}

Status SearchHash1(HashTable H, KeyType K, int *p, int *c) {
    *p = Hash(K);
    int x = *p;
    while(H.elem[*p].key != NULLKEY && K != H.elem[*p].key) {
        *p = x;// 记住原来的值
        Collison1(p, *c);
        (*c)++;
    }
    if(K != H.elem[*p].key) {
        return SUCCESS;
    } else {
        return UNSUCCESS;1
    }
}

Status InsertHash1(HashTable *H, ElemType e) {
    int p = 0;
    int c = 1;
    if(!SearchHash1(*H, e.key, &p, &c)) {// 有重复的key
        return DUPLICATE;
    } else {
        H->elem[p] = e;
        H->count ++;
        return OK;
    }
}

Status SearchHash2(HashTable H, KeyType K, int *p, int *c) {
    *p = Hash(K);
    int x = *p;
    while(H.elem[*p].key != NULLKEY && K != H.elem[*p].key) {
        *p = x;// 记住原来的值
        Collison2(p, *c);
        (*c)++;
    }
    if(K != H.elem[*p].key) {
        return SUCCESS;
    } else {
        return UNSUCCESS;
    }
}

Status InsertHash2(HashTable *H, ElemType e) {
    int p = 0;
    int c = 1;
    if(!SearchHash2(*H, e.key, &p, &c)) {// 有重复的key
        return DUPLICATE;
    } else {
        H->elem[p] = e;
        H->count ++;
        return OK;
    }
}

int Hash(KeyType K) {
    int addr;
    addr = K % 13;
    return addr;
}

void Collison1(int *p, int count) {
    *p = (Hash(*p) + count) % hashsize[1];
}

void Collison2(int *p, int count) {
    int k = (count + 1) / 2;
    if(count % 2 == 1) {
        *p = (*p + (k * k) + hashsize[1]) % hashsize[1];
    } else {
        *p = (*p - (k * k) + hashsize[1]) % hashsize[1];
    }

}

void printHT(HashTable H) {
    for(int i = 0; i < hashsize[H.sizeindex]; i ++) {
        printf("%5d", i);
    }
    printf("\n");
    for(int i = 0; i < hashsize[H.sizeindex]; i ++) {
        printf("%5d", H.elem[i].key);
    }
    printf("\n冲突次数：%d\n", H.count);
    printf("\n");
    printf("\n");
}