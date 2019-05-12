#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAXSIZE 12500

typedef int ElemType;
typedef struct {
	int i, j;
	ElemType e;
}Triple;//三元组

typedef struct {
	Triple data[MAXSIZE + 1];
	int mu, nu, tu;
}TSMatrix;//三元组顺序表
#include "SparseMatrix.h"

Status AllocateSpace(SMatrix*);//为稀疏矩阵分配空间
Status AllocateSpace1(int **, TSMatrix);//为一维数组分配空间
Status Assignment(SMatrix);//给稀疏矩阵赋值
Status SMatToTSMat(SMatrix, TSMatrix *);//稀疏矩阵转换为三元组顺序表
Status TransposeSMatrix(TSMatrix, TSMatrix*);//快速转置稀疏矩阵三元组顺序表
Status AddTSMatrix(TSMatrix, TSMatrix, TSMatrix*);//稀疏矩阵三元组的相加
Status Exchange(Triple *, Triple);//三元组值的交换（赋值）
Status TSMatToSMat(TSMatrix, SMatrix*);//三元组顺序表转换为稀疏矩阵
Status PrintTSMatrix(TSMatrix T);

int main() {
	SMatrix A,B,AA,C;
	TSMatrix TA,TB,TAA,TC;
	printf("创建稀疏矩阵A\n");
	CreateSMatrix(&A);
	PrintSMatrix(A);
	printf("创建稀疏矩阵B\n");
	CreateSMatrix(&B);
	PrintSMatrix(B);
	printf("将稀疏矩阵A转换成三元组\n");
	SMatToTSMat(A, &TA);
	PrintTSMatrix(TA);
	printf("将稀疏矩阵B转换成三元组\n");
	SMatToTSMat(B, &TB);
	PrintTSMatrix(TB);
	printf("将三元组表A快速转置\n");
	TransposeSMatrix(TA, &TAA);
	PrintTSMatrix(TAA);
	printf("三元组顺序表A、B相加\n");
	AddTSMatrix(TA, TB, &TC);
	PrintTSMatrix(TC);
	printf("转置后的三元组顺序表A变为稀疏矩阵\n");
	TSMatToSMat(TAA, &AA);
	PrintSMatrix(AA);
	printf("相加后的三元组顺序表变为稀疏矩阵\n");
	TSMatToSMat(TC, &C);
	PrintSMatrix(C);
	system("pause");
}

Status CreateSMatrix(SMatrix *S) {//创建稀疏矩阵
	printf("请输入你要创建的稀疏矩阵的行和列：\n");
	scanf("%d", &S->i);
	scanf("%d", &S->j);
	AllocateSpace(S);//分配空间
	Assignment(*S);//赋值
	return OK;
}

Status AllocateSpace(SMatrix *S) {//给稀疏矩阵分配空间
	int i = 1;
	int m = S->i + 1,n = S->j + 1;
	S->SSMatrix = (int **)malloc(m * sizeof(int*));
	for (i = 1; i <= m; i++) {
		*(S->SSMatrix + i) = (int *)malloc(n * sizeof(int));
	}
	return OK;
}

Status Assignment(SMatrix S) {//给稀疏矩阵赋值
	if (S.SSMatrix) {
		int i = 1, j = 1;
		srand(time(NULL));
		for (i = 1; i <= S.i; i++) {
			for (j = 1; j <= S.j; j++) {
				if (rand() % 10 > 2) {
					S.SSMatrix[i][j] = 0;
				}
				else {
					S.SSMatrix[i][j] = rand() % 9 + 1;
				}
			}
		}
		return OK;
	}
	else {
		return ERROR;
	}
}

Status PrintSMatrix(SMatrix S) {//打印稀疏矩阵
	int i = 1, j = 1;
	for (i = 1; i <= S.i; i++) {
		for (j = 1; j <= S.j; j++) {
			printf("%3d", S.SSMatrix[i][j]);
		}
		printf("\n");
	}
	return OK;
}

Status SMatToTSMat(SMatrix S, TSMatrix *T) {//稀疏矩阵转换为三元组顺序表
	int i = 1, j = 1,k = 0;
	T->mu = S.i;
	T->nu = S.j;
	T->tu = 0;
	for (i = 1; i <= T->mu; i++) {
		for (j = 1; j <= T->nu; j++) {
			if (S.SSMatrix[i][j] != 0) {
				T->data[++k].e = S.SSMatrix[i][j];
				T->data[k].i = i;
				T->data[k].j = j;
				T->tu++;
			}
		}
	}
	return OK;
}

Status PrintTSMatrix(TSMatrix T) {//打印三元组顺序表
	for (int i = 1; i <= T.tu; i++) {
		printf("(%d,%d,%d),", T.data[i].i, T.data[i].j, T.data[i].e);
	}
	printf("\n");
	return OK;
}

Status TransposeSMatrix(TSMatrix M, TSMatrix *T) {
	int q, p, col;
	int *num = NULL, *cpot = NULL;
	AllocateSpace1(&num,M);
	AllocateSpace1(&cpot, M);
	T->mu = M.nu;
	T->nu = M.mu;
	T->tu = M.tu;
	if (T->tu) {
		for (col = 1; col < M.nu; col++) {
			num[col] = 0;
		}
		for (p = 1; p <= M.tu; p++) {
			++num[M.data[p].j];
		}
		cpot[1] = 1;
		for (col = 2; col <= M.nu; col++) {
			cpot[col] = cpot[col - 1] + num[col - 1];
		}
		for (p = 1; p <= M.tu; p++) {
			col = M.data[p].j;
			q = cpot[col];
			T->data[q].i = M.data[p].j;
			T->data[q].j = M.data[p].i;
			T->data[q].e = M.data[p].e;
			++cpot[col];
		}
	}
	return OK;
}

Status AllocateSpace1(int **p, TSMatrix T) {//为一维数组分配空间
	*p = (int *)malloc((T.tu + 1) * sizeof(int));
	return OK;
}

Status AddTSMatrix(TSMatrix A, TSMatrix B, TSMatrix *C) {//两稀疏矩阵三元组顺序表相加
	if (A.mu != B.mu || A.nu != B.nu) {
		printf("两矩阵的行或列不相等不能相加！\n");
		return ERROR;
	}
	int i = 1, j = 1, k = 1;
	C->mu = A.mu;
	C->nu = A.nu;
	C->tu = 0;
	for (k = 1; i <= A.tu&&j <= B.tu; k++) {
		if (A.data[i].i < B.data[j].i) {
			Exchange(&C->data[k], A.data[i]);
			C->tu++;
			i++;
		}
		else if (A.data[i].i > B.data[j].i) {
			Exchange(&C->data[k], B.data[j]);
			C->tu++;
			j++;
		}
		else {
			if (A.data[i].j < B.data[j].j) {
				Exchange(&C->data[k], A.data[i]);
				C->tu++;
				i++;
			}
			else if (A.data[i].j > B.data[j].j) {
				Exchange(&C->data[k], B.data[j]);
				C->tu++;
				j++;
			}
			else {//行列相等
				C->data[k].i = A.data[i].i;
				C->data[k].j = A.data[i].j;
				C->data[k].e = A.data[i].e + B.data[j].e;
				i++;
				j++;
				C->tu++;
			}
		}
	}
	while (i <= A.tu) {//把A中剩余元素插入
		Exchange(&C->data[k], A.data[i]);
		C->tu++;
		i++;
		k++;
	}
	while (j <= B.tu) {//把B中剩余元素插入
		Exchange(&C->data[k], B.data[j]);
		C->tu++;
		j++;
		k++;
	}
	return OK;
}

Status Exchange(Triple *C, Triple A) {//三元组值的交换（赋值）
	C->i = A.i;
	C->j = A.j;
	C->e = A.e;
	return OK;
}

Status TSMatToSMat(TSMatrix T, SMatrix *S) {//三元组顺序表转换为稀疏矩阵
	int m = T.mu + 1, n = T.nu + 1,i = 1,j = 1;
	S->i = T.mu;
	S->j = T.nu;
	AllocateSpace(S);//分配空间
	for (i = 1; i < m; i++) {//初始化
		for (j = 1; j < n; j++) {
			S->SSMatrix[i][j] = 0;
		}
	}
	for (i = 1; i <= T.tu; i++) {//遍历三元组顺序表赋值
		if (T.data[i].e != 0) {
			S->SSMatrix[T.data[i].i][T.data[i].j] = T.data[i].e;
		}
	}
	return OK;
}






