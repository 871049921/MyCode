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
    RedType *elem;//顺序表的储存空间基址
    int length;//当前长度
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
    t = 0;//希尔排序增量序列长度
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
        if (L->elem[i].key < L->elem[i - 1].key) {//如果比已排好序的最大值小
            sortType->Cnum ++;
            L->elem[0] = L->elem[i];//将当前值放入监视哨
            L->elem[i] = L->elem[i - 1];
            for (j = i - 2; L->elem[j].key > L->elem[0].key; j--) {//在已排好序中找应放位置
                L->elem[j + 1] = L->elem[j];
            }
            L->elem[j + 1] = L->elem[0];//放入正确位置
        }
    }
    return OK;
}

void BInsertSort(SqList *L) {//对顺序表L作折半插入排序
    int i, low, high, m, j;
    for (i = 2; i < L->length; ++i) {
        L->elem[0] = L->elem[i];
        low = 1;
        high = i - 1;//折半查找有序插入的位置
        while (low <= high) {
            m = (low + high) / 2;//折半
            if (L->elem[m].key > L->elem[0].key)
                high = m - 1;              //插入点在低半区
            else
                low = m + 1;             //插入点在高半区
        }
        for (j = i - 1; j >= high + 1; --j)
            L->elem[j + 1] = L->elem[j];          //记录后移
        L->elem[high + 1] = L->elem[0];         //插入
    }
}

void ShellSort(SqList *L, int dlta[], int t) {//按增量序列dlta[0..t-1]对顺序表L作希尔排序
    int k;
    for (k = 0; k <= t; ++k)
        ShellInsert(L, dlta[k]); //一趟增量为dlta[k]的插入排序
}

void ShellInsert(SqList *L, int dk) {//对顺序表L作一趟希尔插入排序。
    int i, j;
    for (i = dk + 1; i <= L->length; ++i)
        if (L->elem[i].key < L->elem[i - dk].key) {
            L->elem[0] = L->elem[i];
            for (j = i - dk; j > 0 && L->elem[0].key < L->elem[j].key; j -= dk)
                L->elem[j + dk] = L->elem[j]; //记录后移，查找插入位置
            L->elem[j + dk] = L->elem[0]; //插入
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

int Partition(SqList *L, int low, int high) {//一趟快速排序
    L->elem[0] = L->elem[low];
    while (low < high) { //从表的两端交替地向中间扫描
        while (low < high && L->elem[high].key >= L->elem[0].key)
            --high;
        L->elem[low] = L->elem[high];//将比枢轴记录小的记录交换到低端
        while (low < high && L->elem[low].key <= L->elem[0].key)
            ++low;
        L->elem[high] = L->elem[low];//将比枢轴记录大的记录交换到高端
    }
    L->elem[low] = L->elem[0];
    return low; //返回枢轴所在位置
}//partition

void QSort(SqList *L, int low, int high) {//对顺序表l中的子序列L.elem[low．．high]作快速排序
    int pivotloc;
    if (low < high) {
        pivotloc = Partition(L, low, high);//将L.elem[low..high]一分为二
        QSort(L, low, pivotloc - 1);  //对低子表递归排序
        QSort(L, pivotloc + 1, high);//对高于表递归排序
    }
}

void QuickSort(SqList *L) { //对顺序表L作快速排序
    QSort(L, 1, L->length);
}

void SelectSort(SqList *L) { //对顺序表L作简单选择排序。
    int i, min, j;
    for (i = 1; i < L->length; ++i) {//选择第i小的记录，并交换到位
        min = i;
        for (j = i + 1; j <= L->length; j++) {
            if (L->elem[min].key > L->elem[j].key)
                min = j;
        }
        if (min != i)
            Swap(&L->elem[min], &L->elem[i]); //与第i个记录交换
    }
}

void HeapSort(SqList *H) { //对顺序表H进行堆排序
    int i;
    for (i = H->length / 2; i > 0;--i) //length/2非'叶子结点'
        HeapAdjust(H,i,H->length);//从下往上建立堆
    for (i = H->length; i > 1; --i) {//将堆顶记录和最后一个记录互换
        Swap(&H->elem[1],&H->elem[i]);
        HeapAdjust(H, 1, i - 1); //将H.elem[1..i-1]重新调整为大顶堆
    }
}

void HeapAdjust(SqList *H, int s, int m) {//调整为大顶堆
    int j;
    H->elem[0] = H->elem[s];
    for (j = 2 * s; j <= m; j = j * 2) { //沿key较大的孩子结点向下筛选,j为key较大的记录的下标
        if (j < m && H->elem[j].key < H->elem[j + 1].key)//选较大的
            ++j;
        if (H->elem[0].key > H->elem[j].key)//如果大则s位置为暂存位放置点
            break;
        H->elem[s] = H->elem[j];
        s = j;//在'子树'寻找
    }
    H->elem[s] = H->elem[0]; //插入
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

Status InitList_SqList(SqList *L) {//创建新的顺序表
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
