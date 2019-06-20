#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include "time.h"

#define OK 1
#define ERROR 0
#define OVERFLOW -1
#define MAXSIZE 50000

typedef int Status;
typedef int KeyType;
typedef int InfoType;
typedef struct {
	KeyType key;
	InfoType *info;
}RedType;
typedef struct {
	RedType *r;
	int length;
}SqList;

int swap_count, compare_count;
Status InitSqList(SqList *);
Status CreateSqList(SqList *);
Status CopySqList(SqList, SqList *);
Status OutputSqList(SqList);
int LT(KeyType, KeyType);
void Swap(RedType *, RedType *);
Status InsertSort(SqList *);
Status BInsertSort(SqList *);
Status ShellInsert(SqList *, int );
Status ShellSort(SqList *, int[], int);
Status BubbleSort(SqList *);
int Partition(SqList *, int, int);
void QSort(SqList *, int, int);
Status QuickSort(SqList *);
Status SelectSort(SqList *);
Status HeapAdjust(SqList *, int, int);
Status HeapSort(SqList *);
Status Merge(SqList *, int, int, int);
void MSort(SqList *, int);
Status MergeSort(SqList *);

void main() {
	SqList LI,LB,LSh,LBu,LQ,LS,LH,LM, L_BAK;
	int dlta[MAXSIZE],t;
	double duration;
	clock_t start, finish;
	InitSqList(&L_BAK);
	CreateSqList(&L_BAK);
	t = 0;
	dlta[0] = L_BAK.length / 2;
	while (dlta[t] > 1) {
		dlta[t + 1] = dlta[t] / 2;
		t++;
	}
	printf("????????          ???????           ??????          ????????\n");
	InitSqList(&LI);
	CopySqList(L_BAK, &LI);
	start = clock();
	InsertSort(&LI);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n??????????      %lf??         %d            %d\n",duration,compare_count,swap_count);
	
	compare_count = swap_count = 0;
	InitSqList(&LB);
	CopySqList(L_BAK, &LB);
	start = clock();
	BInsertSort(&LB);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n??????????      %lf??         %d              %d\n", duration, compare_count, swap_count);

	compare_count = swap_count = 0;
	InitSqList(&LSh);
	CopySqList(L_BAK, &LSh);
	start = clock();
	ShellSort(&LSh,dlta,t + 1);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n???????          %lf??         %d             %d\n", duration, compare_count, swap_count);

	compare_count = swap_count = 0;
	InitSqList(&LBu);
	CopySqList(L_BAK, &LBu);
	start = clock();
	BubbleSort(&LBu);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n???????          %lf??         %d           %d\n", duration, compare_count, swap_count);

	compare_count = swap_count = 0;
	InitSqList(&LQ);
	CopySqList(L_BAK, &LQ);
	start = clock();
	QuickSort(&LQ);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n????????          %lf??         %d             %d\n", duration, compare_count, swap_count);

	compare_count = swap_count = 0;
	InitSqList(&LS);
	CopySqList(L_BAK, &LS);
	start = clock();
	SelectSort(&LS);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n??????????      %lf??         %d            %d\n", duration, compare_count, swap_count);

	compare_count = swap_count = 0;
	InitSqList(&LH);
	CopySqList(L_BAK, &LH);
	start = clock();
	HeapSort(&LH);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n??????            %lf??         %d             %d\n", duration, compare_count, swap_count);

	compare_count = swap_count = 0;
	InitSqList(&LM);
	CopySqList(L_BAK, &LM);
	start = clock();
	MergeSort(&LM);
	finish = clock();
	duration = (double)(finish - start) / CLK_TCK;
	printf("\n?鲢????          %lf??         %d             %d\n", duration, compare_count, swap_count);
	_getch();
}

Status InitSqList(SqList *L) {
	L->r = (RedType *)malloc((MAXSIZE + 1) * sizeof(RedType));
	if (!L->r)
		exit(OVERFLOW);
	L->length = 0;
	return OK;
}

Status CreateSqList(SqList *L) {
	int i;
	srand((unsigned int)time(NULL));
	printf("\n??????????????????????????\n");
	scanf_s("%d", &L->length);
	for (i = 1; i <= L->length; i++)
		L->r[i].key = rand();
	/*printf("\nδ????????????\n");
	for (i = 1; i <= L->length; i++)
		printf("%8d", L->r[i].key);
	printf("\n");*/
	return OK;
}

Status CopySqList(SqList L_BAK, SqList *L) {
	int i;
	if (!L_BAK.length) {
		printf("????????????");
		return ERROR;
	}
	for (i = 1; i <= L_BAK.length; i++)
		L->r[i].key = L_BAK.r[i].key;
	L->length = L_BAK.length;
	return OK;
}

Status OutputSqList(SqList L) {
	int i;
	printf("\n???????????%d\n", L.length);
	printf("\n?????????????\n");
	for (i = 1; i <= L.length; i++)
		printf("%8d", L.r[i].key);
	printf("\n");
	return OK;
}

int LT(KeyType e1, KeyType e2) {
	compare_count++;
	if (e1 < e2)
		return 1;
	else
		return 0;
}

void Swap(RedType *e1, RedType *e2) {
	swap_count++;
	RedType e;
	e = *e1;
	*e1 = *e2;
	*e2 = e;
}

Status InsertSort(SqList *L) {
	int i, j;
	if (!L->length) {
		printf("????????");
		return ERROR;
	}
	for (i = 2; i <= L->length; i++) {
		if (LT(L->r[i].key, L->r[i - 1].key)){
			L->r[0] = L->r[i];
			L->r[i] = L->r[i - 1];
			for (j = i - 2; LT(L->r[0].key, L->r[j].key); j--) {
				L->r[j + 1] = L->r[j];
				swap_count++;
			}
			L->r[j + 1] = L->r[0];
			swap_count++;
		}
	}
	return OK;
}

Status BInsertSort(SqList *L) {
	int i, j, mid, low, high;
	if (!L->length) {
		printf("????????");
		return ERROR;
	}
	for (i = 2; i < L->length; i++) {
		L->r[0] = L->r[i];
		low = 1;
		high = i - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (LT(L->r[0].key, L->r[mid].key))
				high = mid - 1;
			else
				low = mid + 1;
		}
		for (j = i - 1; j >= high + 1; j--) {
			L->r[j + 1] = L->r[j];
			swap_count++;
		}
		L->r[high + 1] = L->r[0];
		swap_count++;
	}
	return OK;
}

Status ShellInsert(SqList *L, int dk) {
	int i, j;
	for (i = dk + 1; i <= L->length; i++) {
		if(LT(L->r[i].key,L->r[i - dk].key)){
			L->r[0] = L->r[i];
			for (j = i - dk; j > 0 && LT(L->r[0].key, L->r[j].key); j -= dk) {
				L->r[j + dk] = L->r[j];
				swap_count++;
			}
			L->r[j + dk] = L->r[0];
			swap_count++;//
		}
	}
	return OK;
}

Status ShellSort(SqList *L, int dlta[], int t) {
	int k;
	if (!L->length) {
		printf("????????");
		return ERROR;
	}
	for (k = 0; k < t; k++)
		ShellInsert(L, dlta[k]);
	return OK;
}

Status BubbleSort(SqList *L) {
	int i, j;
	if (!L->length) {
		printf("????????");
		return ERROR;
	}
	for (i = 1; i < L->length; i++) {
		for (j = 1; j <= L->length - i; j++)
			if (!LT(L->r[j].key, L->r[j + 1].key))
				Swap(&L->r[j], &L->r[j + 1]);
	}
	return OK;
}

int Partition(SqList *L, int low, int high) {
	int pivotkey;
	L->r[0] = L->r[low];
	pivotkey = L->r[low].key;
	while (low < high) {
		while (low < high && L->r[high].key >= pivotkey) {
			compare_count++;
			high--;
		}
		L->r[low] = L->r[high],swap_count++;
		while (low < high && L->r[high].key <= pivotkey) {
			compare_count++;
			low++;
		}
		L->r[high] = L->r[low],swap_count++;
	}
	L->r[low] = L->r[0];
	return low;
}

void QSort(SqList *L, int low, int high) {
	int pivotkey;
	if (low < high) {
		pivotkey = Partition(L, low, high);
		QSort(L, low, pivotkey - 1);
		QSort(L, pivotkey + 1, high);
	}
}

Status QuickSort(SqList *L) {
	if (!L->length) {
		printf("????????");
		return ERROR;
	}
	QSort(L, 1, L->length);
	return OK;
}

Status SelectSort(SqList *L) {
	int i, j, min;
	if (!L->length) {
		printf("????????");
		return ERROR;
	}
	for (i = 1; i < L->length; i++) {
		min = i;
		for (j = i + 1; j <= L->length - i; j++) {
			if (LT(L->r[j].key, L->r[min].key))
				min = j;
		}
		if (min != i)
			Swap(&L->r[i], &L->r[min]);
	}
	return OK;
}

Status HeapAdjust(SqList *H, int s, int m) {
	int j;
	H->r[0] = H->r[s];
	for (j = 2 * s; j <= m; j *= 2) {
		if (j < m && LT(H->r[j].key, H->r[j + 1].key))
			j++;
		if (!LT(H->r[0].key, H->r[j].key))
			break;
		H->r[s] = H->r[j];
		swap_count++;
		s = j;
	}
	H->r[s] = H->r[0];
	return OK;
}

Status HeapSort(SqList *H) {
	int i;
	if (!H->length) {
		printf("????????");
		return ERROR;
	}
	for (i = H->length / 2; i > 0; i--)
		HeapAdjust(H, i, H->length);
	for (i = H->length; i > 1; i--) {
		Swap(&H->r[1], &H->r[i]);
		HeapAdjust(H, 1, i - 1);
	}
	return OK;
}

Status Merge(SqList *L, int low, int mid, int high) {
	int i = low, j = mid + 1, k = 0;
	SqList L1;
	L1.r = (RedType *)malloc((high - low + 1) * sizeof(RedType));
	if (!L1.r)
		exit(OVERFLOW);
	while ((i <= mid && j <= high)) {
		compare_count++;
		L1.r[k++] = LT(L->r[i].key, L->r[j].key) ? L->r[i++] : L->r[j++];
		swap_count++;
	}
	while (i <= mid) {
		compare_count++;
		L1.r[k++] = L->r[i++];
		swap_count++;
	}
	while (j <= high) {
		compare_count++;
		L1.r[k++] = L->r[j++];
		swap_count++;
	}
	for (k = 0, i = low; i <= high; k++, i++) {
		L->r[i].key = L1.r[k].key;
	}
	return OK;
}

void MSort(SqList *L, int len) {
	int i;
	for (i = 1; i + 2 * len - 1 <= L->length; i = i + 2 * len)
		Merge(L, i, i + len - 1, i + 2 * len - 1);
	if (i + len - 1 < L->length)
		Merge(L, i, i + len - 1, L->length);
}

Status MergeSort(SqList *L) {
	int len;
	if (!L->length) {
		printf("????????");
		return ERROR;
	}
	for (len = 1; len < L->length; len *= 2)
		MSort(L, len);
	return OK;
}