#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAXSIZE 12500

typedef int ElemType;
typedef struct {
	int i, j;
	ElemType e;
}Triple;//��Ԫ��

typedef struct {
	Triple data[MAXSIZE + 1];
	int mu, nu, tu;
}TSMatrix;//��Ԫ��˳���
#include "SparseMatrix.h"

Status AllocateSpace(SMatrix*);//Ϊϡ��������ռ�
Status AllocateSpace1(int **, TSMatrix);//Ϊһά�������ռ�
Status Assignment(SMatrix);//��ϡ�����ֵ
Status SMatToTSMat(SMatrix, TSMatrix *);//ϡ�����ת��Ϊ��Ԫ��˳���
Status TransposeSMatrix(TSMatrix, TSMatrix*);//����ת��ϡ�������Ԫ��˳���
Status AddTSMatrix(TSMatrix, TSMatrix, TSMatrix*);//ϡ�������Ԫ������
Status Exchange(Triple *, Triple);//��Ԫ��ֵ�Ľ�������ֵ��
Status TSMatToSMat(TSMatrix, SMatrix*);//��Ԫ��˳���ת��Ϊϡ�����
Status PrintTSMatrix(TSMatrix T);

int main() {
	SMatrix A,B,AA,C;
	TSMatrix TA,TB,TAA,TC;
	printf("����ϡ�����A\n");
	CreateSMatrix(&A);
	PrintSMatrix(A);
	printf("����ϡ�����B\n");
	CreateSMatrix(&B);
	PrintSMatrix(B);
	printf("��ϡ�����Aת������Ԫ��\n");
	SMatToTSMat(A, &TA);
	PrintTSMatrix(TA);
	printf("��ϡ�����Bת������Ԫ��\n");
	SMatToTSMat(B, &TB);
	PrintTSMatrix(TB);
	printf("����Ԫ���A����ת��\n");
	TransposeSMatrix(TA, &TAA);
	PrintTSMatrix(TAA);
	printf("��Ԫ��˳���A��B���\n");
	AddTSMatrix(TA, TB, &TC);
	PrintTSMatrix(TC);
	printf("ת�ú����Ԫ��˳���A��Ϊϡ�����\n");
	TSMatToSMat(TAA, &AA);
	PrintSMatrix(AA);
	printf("��Ӻ����Ԫ��˳����Ϊϡ�����\n");
	TSMatToSMat(TC, &C);
	PrintSMatrix(C);
	system("pause");
}

Status CreateSMatrix(SMatrix *S) {//����ϡ�����
	printf("��������Ҫ������ϡ�������к��У�\n");
	scanf("%d", &S->i);
	scanf("%d", &S->j);
	AllocateSpace(S);//����ռ�
	Assignment(*S);//��ֵ
	return OK;
}

Status AllocateSpace(SMatrix *S) {//��ϡ��������ռ�
	int i = 1;
	int m = S->i + 1,n = S->j + 1;
	S->SSMatrix = (int **)malloc(m * sizeof(int*));
	for (i = 1; i <= m; i++) {
		*(S->SSMatrix + i) = (int *)malloc(n * sizeof(int));
	}
	return OK;
}

Status Assignment(SMatrix S) {//��ϡ�����ֵ
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

Status PrintSMatrix(SMatrix S) {//��ӡϡ�����
	int i = 1, j = 1;
	for (i = 1; i <= S.i; i++) {
		for (j = 1; j <= S.j; j++) {
			printf("%3d", S.SSMatrix[i][j]);
		}
		printf("\n");
	}
	return OK;
}

Status SMatToTSMat(SMatrix S, TSMatrix *T) {//ϡ�����ת��Ϊ��Ԫ��˳���
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

Status PrintTSMatrix(TSMatrix T) {//��ӡ��Ԫ��˳���
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

Status AllocateSpace1(int **p, TSMatrix T) {//Ϊһά�������ռ�
	*p = (int *)malloc((T.tu + 1) * sizeof(int));
	return OK;
}

Status AddTSMatrix(TSMatrix A, TSMatrix B, TSMatrix *C) {//��ϡ�������Ԫ��˳������
	if (A.mu != B.mu || A.nu != B.nu) {
		printf("��������л��в���Ȳ�����ӣ�\n");
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
			else {//�������
				C->data[k].i = A.data[i].i;
				C->data[k].j = A.data[i].j;
				C->data[k].e = A.data[i].e + B.data[j].e;
				i++;
				j++;
				C->tu++;
			}
		}
	}
	while (i <= A.tu) {//��A��ʣ��Ԫ�ز���
		Exchange(&C->data[k], A.data[i]);
		C->tu++;
		i++;
		k++;
	}
	while (j <= B.tu) {//��B��ʣ��Ԫ�ز���
		Exchange(&C->data[k], B.data[j]);
		C->tu++;
		j++;
		k++;
	}
	return OK;
}

Status Exchange(Triple *C, Triple A) {//��Ԫ��ֵ�Ľ�������ֵ��
	C->i = A.i;
	C->j = A.j;
	C->e = A.e;
	return OK;
}

Status TSMatToSMat(TSMatrix T, SMatrix *S) {//��Ԫ��˳���ת��Ϊϡ�����
	int m = T.mu + 1, n = T.nu + 1,i = 1,j = 1;
	S->i = T.mu;
	S->j = T.nu;
	AllocateSpace(S);//����ռ�
	for (i = 1; i < m; i++) {//��ʼ��
		for (j = 1; j < n; j++) {
			S->SSMatrix[i][j] = 0;
		}
	}
	for (i = 1; i <= T.tu; i++) {//������Ԫ��˳���ֵ
		if (T.data[i].e != 0) {
			S->SSMatrix[T.data[i].i][T.data[i].j] = T.data[i].e;
		}
	}
	return OK;
}






