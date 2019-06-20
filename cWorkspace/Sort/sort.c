#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <conio.h>

#define OVERFLOW    -1
#define TRUE    1
#define FALSE   0
#define OK  1
#define ERROR   -1
#define MAXSIZE 50000

typedef int keyType;
typedef int Status;
typedef struct {
    keyType key;
} RedType;
typedef struct {
    RedType *elem;//˳���Ĵ���ռ��ַ
    int length;//��ǰ����
} SqList;
typedef struct {
    double time;
    int Cnum;
    int Enum;
}SortType;

Status InsertSort(SqList *L,SortType *sortType);

void ShellInsert(SqList *L, int dk);

void ShellSort(SqList *L, int dlta[], int t);

void BInsertSort(SqList *L);

Status BubbleSort(SqList *L);

void QuickSort(SqList *L);

void SelectSort(SqList *L);

void QSort(SqList *L, int low, int high);

int Partition(SqList *L, int low, int high);

void HeapSort(SqList *H);

void HeapAdjust(SqList *H, int s, int m);

Status CopySqList(SqList L_BAK,SqList *L);

void Swap(RedType *e1, RedType *e2);

Status CreateSqList(SqList *L);

Status InitList_SqList(SqList *L);

Status OutputSqList(SqList L);

int main() {
    SqList L,L_BAK;
    int dlta[MAXSIZE],t,i,Len = 7;
    clock_t start,finish;
    SortType *sortType = (SortType *)malloc(sizeof(SortType) * Len);
    InitList_SqList(&L);
    InitList_SqList(&L_BAK);
    CreateSqList(&L_BAK);
    t = 0;//ϣ�������������г���
    dlta[0] = L_BAK.length/2;
    while (dlta[t] > 1){
        dlta[t + 1] = dlta[t] / 2;
        t ++;
    }
    CopySqList(L_BAK,&L);
    start = clock();
    InsertSort(&L,&sortType[1]);
    finish = clock();
    sortType[0].time = (double)(finish - start)/CLK_TCK;
    CopySqList(L_BAK,&L);
    start = clock();
    BInsertSort(&L);
    finish = clock();
    sortType[1].time = (double)(finish - start)/CLK_TCK;
    CopySqList(L_BAK,&L);
    start = clock();
    ShellSort(&L,dlta,t);
    finish = clock();
    sortType[2].time = (double)(finish - start)/CLK_TCK;
    CopySqList(L_BAK,&L);
    start = clock();
    BubbleSort(&L);
    finish = clock();
    sortType[3].time = (double)(finish - start)/CLK_TCK;
    CopySqList(L_BAK,&L);
    start = clock();
    QuickSort(&L);
    finish = clock();
    sortType[4].time = (double)(finish - start)/CLK_TCK;
    CopySqList(L_BAK,&L);
    start = clock();
    SelectSort(&L);
    finish = clock();
    sortType[5].time = (double)(finish - start)/CLK_TCK;
//    CopySqList(L_BAK,&L);
//    start = clock();
//    HeapSort(&L);
//    finish = clock();
//    sortType[6].time = (double)(finish - start)/CLK_TCK;

    printf("Sorting method\tRunning time\tNumber of comparisons\tExchange times\n");
    for (i = 0;i < 6;i ++){
//        i = 3;
        printf("%d\t%1f\t%d\t%d\t",i,sortType[i].time,sortType[i].Cnum,sortType[i].Enum);
        printf("\n");
    }
//    OutputSqList(L);
}

Status InsertSort(SqList *L,SortType *sortType) {
    int i, j;
    if (!L->length)
        return ERROR;
    for (i = 2; i <= L->length; i++) {
        if (L->elem[i].key < L->elem[i - 1].key) {//��������ź�������ֵС
            sortType->Cnum ++;
            L->elem[0] = L->elem[i];//����ǰֵ���������
            L->elem[i] = L->elem[i - 1];
            for (j = i - 2; L->elem[j].key > L->elem[0].key; j--) {//�����ź�������Ӧ��λ��
                L->elem[j + 1] = L->elem[j];
            }
            L->elem[j + 1] = L->elem[0];//������ȷλ��
        }
    }
    return OK;
}

void BInsertSort(SqList *L) {//��˳���L���۰��������
    int i, low, high, m, j;
    for (i = 2; i < L->length; ++i) {
        L->elem[0] = L->elem[i];
        low = 1;
        high = i - 1;//�۰������������λ��
        while (low <= high) {
            m = (low + high) / 2;//�۰�
            if (L->elem[m].key > L->elem[0].key)
                high = m - 1;              //������ڵͰ���
            else
                low = m + 1;             //������ڸ߰���
        }
        for (j = i - 1; j >= high + 1; --j)
            L->elem[j + 1] = L->elem[j];          //��¼����
        L->elem[high + 1] = L->elem[0];         //����
    }
}

void ShellSort(SqList *L, int dlta[], int t) {//����������dlta[0..t-1]��˳���L��ϣ������
    int k;
    for (k = 0; k <= t; ++k)
        ShellInsert(L, dlta[k]); //һ������Ϊdlta[k]�Ĳ�������
}

void ShellInsert(SqList *L, int dk) {//��˳���L��һ��ϣ����������
    int i, j;
    for (i = dk + 1; i <= L->length; ++i)
        if (L->elem[i].key < L->elem[i - dk].key) {
            L->elem[0] = L->elem[i];
            for (j = i - dk; j > 0 && L->elem[0].key < L->elem[j].key; j -= dk)
                L->elem[j + dk] = L->elem[j]; //��¼���ƣ����Ҳ���λ��
            L->elem[j + dk] = L->elem[0]; //����
        }
}

Status BubbleSort(SqList *L) {
    int i, j;
    if (!L->length)
        return ERROR;
    for (i = 1; i < L->length; ++i) {
        for (j = 1; j <= L->length - i; ++j) {
            if (L->elem[j].key > L->elem[j + 1].key) {
                Swap(&L->elem[j], &L->elem[j + 1]);
            }
        }
    }
    return OK;
}

int Partition(SqList *L, int low, int high) {//һ�˿�������
    L->elem[0] = L->elem[low];
    while (low < high) { //�ӱ�����˽�������м�ɨ��
        while (low < high && L->elem[high].key >= L->elem[0].key)
            --high;
        L->elem[low] = L->elem[high];//���������¼С�ļ�¼�������Ͷ�
        while (low < high && L->elem[low].key <= L->elem[0].key)
            ++low;
        L->elem[high] = L->elem[low];//���������¼��ļ�¼�������߶�
    }
    L->elem[low] = L->elem[0];
    return low; //������������λ��
}//partition

void QSort(SqList *L, int low, int high) {//��˳���l�е�������L.elem[low����high]����������
    int pivotloc;
    if (low < high) {
        pivotloc = Partition(L, low, high);//��L.elem[low..high]һ��Ϊ��
        QSort(L, low, pivotloc - 1);  //�Ե��ӱ�ݹ�����
        QSort(L, pivotloc + 1, high);//�Ը��ڱ�ݹ�����
    }
}

void QuickSort(SqList *L) { //��˳���L����������
    QSort(L, 1, L->length);
}

void SelectSort(SqList *L) { //��˳���L����ѡ������
    int i, min, j;
    for (i = 1; i < L->length; ++i) {//ѡ���iС�ļ�¼����������λ
        min = i;
        for (j = i + 1; j <= L->length; j++) {
            if (L->elem[min].key > L->elem[j].key)
                min = j;
        }
        if (min != i)
            Swap(&L->elem[min], &L->elem[i]); //���i����¼����
    }
}

void HeapSort(SqList *H) { //��˳���H���ж�����
    int i;
    for (i = H->length / 2; i > 0;--i) //length/2��'Ҷ�ӽ��'
        HeapAdjust(H,i,H->length);//�������Ͻ�����
    for (i = H->length; i > 1; --i) {//���Ѷ���¼�����һ����¼����
        Swap(&H->elem[1],&H->elem[i]);
        HeapAdjust(H, 1, i - 1); //��H.elem[1..i-1]���µ���Ϊ�󶥶�
    }
}

void HeapAdjust(SqList *H, int s, int m) {//����Ϊ�󶥶�
    int j;
    H->elem[0] = H->elem[s];
    for (j = 2 * s; j <= m; j = j * 2) { //��key�ϴ�ĺ��ӽ������ɸѡ,jΪkey�ϴ�ļ�¼���±�
        if (j < m && H->elem[j].key < H->elem[j + 1].key)//ѡ�ϴ��
            ++j;
        if (H->elem[0].key > H->elem[j].key)//�������sλ��Ϊ�ݴ�λ���õ�
            break;
        H->elem[s] = H->elem[j];
        s = j;//��'����'Ѱ��
    }
    H->elem[s] = H->elem[0]; //����
}







Status CopySqList(SqList L_BAK,SqList *L){
    int i;
    if (!L_BAK.length){
        return ERROR;
    }
    for (i = 1;i <= L_BAK.length;i ++){
        L->elem[i] = L_BAK.elem[i];
    }
    L->length = L_BAK.length;
    return OK;
}

void Swap(RedType *e1, RedType *e2) {
    RedType e;
    e = *e1;
    *e1 = *e2;
    *e2 = e;
}

Status InitList_SqList(SqList *L) {//�����µ�˳���
    L->elem = (RedType *) malloc((MAXSIZE + 1) * sizeof(RedType));
    if (!L->elem) {
        exit(OVERFLOW);
    }
    L->length = 0;
    return OK;
}

Status CreateSqList(SqList *L) {
    int i;
    srand(time(NULL));
    L->length = MAXSIZE;
    for (i = 1; i <= L->length; i++)
        L->elem[i].key = rand();
//    printf("\nThe UnSorted data is:\n");
//    for (i = 1; i <= L->length; i++) {
//        printf("%8d", L->elem[i].key);
//    }
    return OK;
}

Status OutputSqList(SqList L){
    int i;
    printf("\nThe Sorted data is:\n");
    for (i = 1; i <= L.length; i++) {
        printf("%8d", L.elem[i].key);
    }
}
