typedef struct {
    int row;
    int col;
    int value;
} Triple;

#define MAXSIZE 10000

typedef struct {
    Triple data[MAXSIZE + 1];
    int mu, nu, tu;
} TSMatrix;//三元组顺序表

typedef Triple ElemType;

#include "SequentialList.h"
#include "SparseMatrix.h"
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void CreateSparseMatrix(SMatrix *);// 创建稀疏矩阵
void AllocateSpaceToMatrix(SMatrix *);//  给数组分配空间
void EvaluateToMatrix(SMatrix *);// 给数组赋值
void ToTriple(SMatrix, TSMatrix *);// 矩阵变为三元组
void ToMatrix(TSMatrix, SMatrix *);// 三元组变为矩阵
void Transpose(TSMatrix, TSMatrix *);// 转置矩阵
void AddMatrix(TSMatrix, TSMatrix, TSMatrix *);// 矩阵相加
void printTriple(TSMatrix);// 打印三元组
void printMatrix(SMatrix);// 打印矩阵

int main() {
    SMatrix SA,SB,SAT,SC;
    TSMatrix TA,TB,TAT,TC;
    CreateSparseMatrix(&SA);
    CreateSparseMatrix(&SB);
    ToTriple(SA, &TA);
    ToTriple(SB, &TB);
    Transpose(TA, &TAT);
    AddMatrix(TA, TB, &TC);
    ToMatrix(TAT, &SAT);
    ToMatrix(TC, &SC);

    printf("a\n");
    printMatrix(SA);
    printf("b\n");
    printMatrix(SB);
    printf("ta\n");
    printTriple(TA);
    printf("tb\n");
    printTriple(TB);
    printf("tat\n");
    printTriple(TAT);
    printf("tc\n");
    printTriple(TC);
    printf("sat\n");
    printMatrix(SAT);
    printf("sc\n");
    printMatrix(SC);
}

void CreateSparseMatrix(SMatrix *matrix) {
    printf("请输入行和列");
    scanf("%d", &matrix->i);
    scanf("%d", &matrix->j);
    AllocateSpaceToMatrix(matrix);
    EvaluateToMatrix(matrix);
}

void AllocateSpaceToMatrix(SMatrix *matrix) {
    int row = matrix->i + 1;
    int col = matrix->j + 1;
    matrix->SSMatrix = (int **)malloc(row * sizeof(int *));
    for (int i = 1; i <= row; i++) {
        *(matrix->SSMatrix + i) = (int *)malloc(col * sizeof(int));
    }
}

void EvaluateToMatrix(SMatrix *matrix) {
    srand((int)time(NULL));
    for(int i = 1; i <= matrix->i; i ++) {
        for(int j = 1; j <= matrix->j; j ++) {
            *(*(matrix->SSMatrix + i) + j) = (rand() % 10 == 1) ? rand() % 20 : 0;
        }
    }
}

void ToTriple(SMatrix matrix, TSMatrix *ts) {
    int k = 1;
    ts->mu = matrix.i;
    ts->nu = matrix.j;
    ts->tu = 0;
    for(int i = 1; i <= matrix.i; i ++) {
        for(int j = 1; j <= matrix.j; j ++) {
            if(*(*(matrix.SSMatrix + i) + j) != 0) {
                ts->data[k].value = *(*(matrix.SSMatrix + i) + j);
                ts->data[k].row = i;
                ts->data[k].col = j;
                k ++;
                ts->tu ++;
            }
        }
    }
}

void ToMatrix(TSMatrix ts, SMatrix *matrix) {
    matrix->i = ts.mu;
    matrix->j = ts.nu;
    AllocateSpaceToMatrix(matrix);
    for (int i = 1; i <= matrix->i; i++) {
        for (int j = 1; j <= matrix->j; j++) {
            *(*(matrix->SSMatrix + i) + j) = 0;
        }
    }
    for(int i = 1; i <= ts.tu; i ++) {
        *(*(matrix->SSMatrix + ts.data[i].row) + ts.data[i].col) = ts.data[i].value;
    }
}

void Transpose(TSMatrix M, TSMatrix *T) {
    int *num = (int *)malloc(sizeof(int) * M.nu);
    int *copt = (int *)malloc(sizeof(int) * M.nu);
    T->mu = M.mu;
    T->nu = M.nu;
    T->tu = M.tu;
    if(T->tu) {
        for(int col = 1; col < M.nu; col ++) {
            num[col] = 0;
        }
        for(int t = 1; t <= M.tu; t ++) {
            ++ num[M.data[t].col];
        }
        copt[1] = 1;
        for(int col = 2; col <= M.nu; col ++) {
            copt[col] = copt[col - 1] + num[col - 1];
        }
        for(int i = 1; i <= M.tu; i ++) {
            int col = M.data[i].col;
            int q = copt[col];
            T->data[q].row = M.data[i].col;
            T->data[q].col = M.data[i].row;
            T->data[q].value = M.data[i].value;
            ++ copt[col];
        }
    }
}

void AddMatrix(TSMatrix A,TSMatrix B,TSMatrix *C) {
    int ai,bi,ci,aj,bj,ap,bp,cp;
    ap = bp = cp = 1;
    C->mu = A.mu;
    C->nu = A.nu;
    while(ap <= A.tu && bp <= B.tu){
        ai = A.data[ap].row;
        bi = B.data[bp].row;
        if(ai > bi){
            ci = bi;
            while(ci == B.data[bp].row){
                C->data[cp].row = ci;
                C->data[cp].col = B.data[bp].col;
                C->data[cp].value = B.data[bp].value;
                ++bp;
                ++cp;
            }
        } else if(ai < bi){
            ci = ai;
            while(ci == A.data[ap].row){
                C->data[cp].row = ci;
                C->data[cp].col = A.data[ap].col;
                C->data[cp].value = A.data[ap].value;
                ++ap;
                ++cp;
            }
        } else if(ai == bi){
            ci = ai;
            aj = A.data[ap].col;
            bj = B.data[bp].col;
            if(aj > bj){
                C->data[cp].row = ci;
                C->data[cp].col = bj;
                C->data[cp].value = B.data[bp].value;
                ++cp;
                ++bp;
            } else if(aj < bj){
                C->data[cp].row = ci;
                C->data[cp].col = aj;
                C->data[cp].value = A.data[ap].value;
                ++cp;
                ++ap;
            } else if(aj == bj){
                if(A.data[ap].value + B.data[bp].value != 0){
                    C->data[cp].row = ci;
                    C->data[cp].col = aj;
                    C->data[cp].value = A.data[ap].value + B.data[bp].value;
                    ++cp;
                }
                ++ap;
                ++bp;
            }
        }
    }
    while(ap <= A.tu){
        C->data[cp].row = A.data[ap].row;
        C->data[cp].col = A.data[ap].col;
        C->data[cp].value = A.data[ap].value;
        ++cp;
        ++ap;
    }
    while(bp <= B.tu){
        C->data[cp].row = B.data[bp].row;
        C->data[cp].col = B.data[bp].col;
        C->data[cp].value = B.data[bp].value;
        ++cp;
        ++bp;
    }
    C->tu = --cp;
}

void printTriple(TSMatrix ts) {
    for (int i = 1; i <= ts.tu; i++) {
        printf("(%d,%d,%d),", ts.data[i].row, ts.data[i].col, ts.data[i].value);
    }
    printf("\b ");
    printf("\n");
}

void printMatrix(SMatrix matrix) {
    for (int i = 1; i <= matrix.i; i++) {
        for (int j = 1; j <= matrix.j; j++) {
            printf("%5d", matrix.SSMatrix[i][j]);
        }
        printf("\n");
    }
}