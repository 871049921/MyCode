#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <time.h>

#define MAXSIZE 5000

int comparsionTimes, ExchangeTimes;

void CreateData(int *data);// ��������
void CopyData(int *data, int *copy);// ��������

//ð������
void bubleSort(int data[], int n);

//��������
void quickSort(int data[], int low, int high);

int findPos(int data[], int low, int high);

// ��������
void InsertSort(int data[], int n);

//�۰��������
void bInsertSort(int data[], int n);

//ϣ������
void shellSort(int data[], int n);

//ѡ������
void selectSort(int data[], int n);

//������
void heapSort(int data[], int n);

void swap(int data[], int i, int j);

void heapAdjust(int data[], int i, int n);

//�鲢����
void mergeSort(int data[], int first, int last);

void merge(int data[], int low, int mid, int high);

void printInform(char *type, double finish, double start, int comparsionTimes, int ExchangeTimes);

int main() {
    int data[MAXSIZE], bubleSortArr[MAXSIZE], quickSortArr[MAXSIZE], bInsertSortArr[MAXSIZE], shellSortArr[MAXSIZE],
            selectSortArr[MAXSIZE], heapSortArr[MAXSIZE], mergeSortArr[MAXSIZE], InsertSortArr[MAXSIZE];
    double start, finish;
    CreateData(data);// ��������

    // ð������
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, bubleSortArr);
    start = clock();
    bubleSort(bubleSortArr, MAXSIZE);
    finish = clock();
    printInform("bubleSort", finish, start, comparsionTimes, ExchangeTimes);

    //��������
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, quickSortArr);
    start = clock();
    quickSort(quickSortArr, 0, MAXSIZE - 1);
    finish = clock();
    printInform("quickSort", finish, start, comparsionTimes, ExchangeTimes);

    //��������
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, InsertSortArr);
    start = clock();
    InsertSort(InsertSortArr, MAXSIZE);
    finish = clock();
    printInform("InsertSort", finish, start, comparsionTimes, ExchangeTimes);

    //�۰��������
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, bInsertSortArr);
    start = clock();
    bInsertSort(bInsertSortArr, MAXSIZE);
    finish = clock();
    printInform("bInsertSort", finish, start, comparsionTimes, ExchangeTimes);

    //ϣ������
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, shellSortArr);
    start = clock();
    shellSort(shellSortArr, MAXSIZE);
    finish = clock();
    printInform("shellSort", finish, start, comparsionTimes, ExchangeTimes);

    //ѡ������
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, selectSortArr);
    start = clock();
    selectSort(selectSortArr, MAXSIZE);
    finish = clock();
    printInform("selectSort", finish, start, comparsionTimes, ExchangeTimes);

    //������
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, heapSortArr);
    start = clock();
    heapSort(heapSortArr, MAXSIZE);
    finish = clock();
    printInform("heapSort", finish, start, comparsionTimes, ExchangeTimes);

    //�鲢����
    comparsionTimes = ExchangeTimes = 0;
    CopyData(data, mergeSortArr);
    start = clock();
    mergeSort(mergeSortArr, 0, MAXSIZE - 1);
    finish = clock();
    printInform("mergeSort", finish, start, comparsionTimes, ExchangeTimes);
}

void printInform(char *type, double finish, double start, int comparsionTimes, int ExchangeTimes) {
    printf("%s\t\tTime\t\tcomparsionTimes\t\tExchangeTimes\n", type);
    printf("\t\t%lf\t\t%d\t\t\t%d\n", (double) (finish - start) / CLK_TCK, comparsionTimes, ExchangeTimes);
}

void CreateData(int *data) {
    srand(time(NULL));
    for (int i = 0; i < MAXSIZE; i++) {
        data[i] = rand();
    }
}

void CopyData(int *data, int *copy) {
    for (int i = 0; i < MAXSIZE; i++) {
        copy[i] = data[i];
    }
}

void bubleSort(int data[], int n) {
    int i, j, temp;
    for (j = 0; j < n - 1; j++) {
        for (i = 0; i < n - j - 1; i++) {
            comparsionTimes++;// �Ƚϴ�����һ
            if (data[i] > data[i + 1]) {
                ExchangeTimes += 3;// ����������һ
                temp = data[i];
                data[i] = data[i + 1];
                data[i + 1] = temp;
            }
        }
    }
}

/*--------------------��������---------------------*/
int findPos(int data[], int low, int high) {
    int t = data[low];
    while (low < high) {
        while (low < high && data[high] >= t) {
            comparsionTimes++;// �Ƚϴ�����һ
            high--;
        }
        ExchangeTimes++;// ����������һ
        data[low] = data[high];
        while (low < high && data[low] <= t) {
            comparsionTimes++;// �Ƚϴ�����һ
            low++;
        }
        ExchangeTimes++;// ����������һ
        data[high] = data[low];
    }
    data[low] = t;
    return low;
}

void quickSort(int data[], int low, int high) {
    if (low > high) {
        return;
    }
    int pos = findPos(data, low, high);
    quickSort(data, low, pos - 1);
    quickSort(data, pos + 1, high);
}

/*--------------------��������---------------------*/
void InsertSort(int data[], int n) {
    int i, j;
    int temp;
    for (i = 1; i < n; i++) {
        comparsionTimes++;// �Ƚϴ�����һ
        ExchangeTimes++;// ����������һ
        temp = data[i];
        j = i - 1;
        while (j > -1 && temp < data[j]) {
            comparsionTimes++;// �Ƚϴ�����һ
            ExchangeTimes++;// ����������һ
            data[j + 1] = data[j];
            j--;
        }
        ExchangeTimes++;// ����������һ
        data[j + 1] = temp;
    }
}

/*--------------------�۰��������---------------------*/
void bInsertSort(int data[], int n) {
    int low, high, mid;
    int temp, i, j;
    for (i = 1; i < n; i++) {
        low = 0;
        ExchangeTimes++;// ����������һ
        temp = data[i];
        high = i - 1;
        while (low <= high) {
            comparsionTimes++;// �Ƚϴ�����һ
            mid = (low + high) / 2;
            if (data[mid] > temp) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        int j = i;
        while ((j > low) && data[j - 1] > temp) {
            ExchangeTimes++;// ����������һ
            data[j] = data[j - 1];
            --j;
        }
        ExchangeTimes++;// ����������һ
        data[low] = temp;
    }
}

/*--------------------ϣ������---------------------*/
void shellSort(int *data, int n) {
    int step, i, j, key;
    //�����鰴��step���飬���϶��ֵ�ÿ��ֻʣ��һ��Ԫ��
    for (step = n / 2; step > 0; step /= 2) {
        comparsionTimes++;// �Ƚϴ�����һ
        //��ÿ���е�Ԫ������С����ǰ
        for (i = step; i < n; i++) {
            comparsionTimes++;// �Ƚϴ�����һ
            ExchangeTimes++;// ����������һ
            key = data[i];
            for (j = i - step; j >= 0 && key < data[j]; j -= step) {
                comparsionTimes++;// �Ƚϴ�����һ
                data[j + step] = data[j];
            }
            //�������forѭ��һ�𣬽�����С��Ԫ�ػ��������ǰ��
            ExchangeTimes++;// ����������һ
            data[j + step] = key;
        }
    }
}

/*--------------------ѡ������---------------------*/
void selectSort(int data[], int n) {
    int i, j, mix, temp;
    for (i = 0; i < n - 1; i++) {
        //������СԪ�ص��±�
        int mix = i;
        for (j = i + 1; j < n; j++) {
            comparsionTimes++;// �Ƚϴ�����һ
            if (data[j] < data[mix]) {
                mix = j;
            }
        }
        if (i != mix) {
            ExchangeTimes += 3;// ����������һ
            temp = data[i];
            data[i] = data[mix];
            data[mix] = temp;
        }
    }
}

/*--------------------������---------------------*/
void heapSort(int data[], int n) {
    int i;
    for (i = n / 2; i > 0; i--) {
        heapAdjust(data, i, n);
    }
    for (i = n; i > 1; i--) {
        swap(data, 1, i);
        heapAdjust(data, 1, i - 1);
    }
}

//��������
void swap(int data[], int i, int j) {
    int temp;
    ExchangeTimes += 3;// ����������һ
    temp = data[i];
    data[i] = data[j];
    data[j] = temp;
}

void heapAdjust(int data[], int i, int n) {
    int j, temp;
    temp = data[i];
    for (j = 2 * i; j <= n; j *= 2) {
        comparsionTimes++;// �Ƚϴ�����һ
        if (j < n && data[j] < data[j + 1]) {
            j++;
        }
        comparsionTimes++;// �Ƚϴ�����һ
        if (temp >= data[j]) {
            break;
        }
        ExchangeTimes++;// ����������һ
        data[i] = data[j];
        i = j;
    }
    data[i] = temp;
}

/*--------------------�鲢����---------------------*/
void mergeSort(int data[], int first, int last) {
    int mid = 0;
    if (first < last) {
        mid = (first + last) / 2;
        mergeSort(data, first, mid);
        mergeSort(data, mid + 1, last);
        merge(data, first, mid, last);
    }
    return;
}

void merge(int data[], int low, int mid, int high) {
    int i, k;
    int *temp = (int *) malloc((high - low + 1) * sizeof(int));
    int left_low = low;
    int left_high = mid;
    int right_low = mid + 1;
    int right_high = high;
    for (k = 0; left_low <= left_high && right_low <= right_high; k++) {
        comparsionTimes++;// �Ƚϴ�����һ
        if (data[left_low] <= data[right_low]) {
            ExchangeTimes++;// ����������һ
            temp[k] = data[left_low++];
        } else {
            ExchangeTimes++;// ����������һ
            temp[k] = data[right_low++];
        }
    }
    if (left_low <= left_high) {
        for (i = left_low; i <= left_high; i++) {
            ExchangeTimes++;// ����������һ
            temp[k++] = data[i];
        }
    }
    if (right_low <= right_high) {
        for (i = right_low; i <= right_high; i++) {
            ExchangeTimes++;// ����������һ
            temp[k++] = data[i];
        }
    }
    for (i = 0; i < high - low + 1; i++) {
        ExchangeTimes++;// ����������һ
        data[low + i] = temp[i];
    }
    free(temp);
    return;
}