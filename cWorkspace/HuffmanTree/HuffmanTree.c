#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MaxSize 50
typedef struct {
    char c;
    int w;
    char code[MaxSize];
} HuffCode[MaxSize];

typedef struct {
    int Weight;
    int LChild, RChild, Parent;
} HTNode, HuffTree[MaxSize];

void HuffmanTree(HuffTree HT, int length, HuffCode hc);// 建树
void SelectHTNode(HuffTree HT, int n, int *min1, int *min2);// 选最小的
void HuffmanCode(HuffTree HT, int len, HuffCode hc);// 编码
void HuffmanDecode(char *code, int len, HuffCode hc);// 解码

int main(void) {
    HuffTree HT;
    HuffCode HC;
    int i, len;
    char code[100];
    printf("\n输入字符串字符数量：");
    scanf("%d", &len);
    system("cls");
    printf("代码数量：%2d\n\n", len);
    for (i = 1; i <= len; i++) {
        while (getchar() != '\n') NULL;
        printf("No.%2d： ", i);
        HC[i].c = getchar();
        scanf("%d", &HC[i].w);
    }
    HuffmanTree(HT, len, HC);
    HuffmanCode(HT, len, HC);
    printf("\n输出Huffman编码：\n");
    for (i = 1; i <= len; i++) {
        printf("\n %c :", HC[i].c);
        puts(HC[i].code);
    }
    while(1) {
        printf("输入编码");
        fflush(stdin);
        scanf("%s", code);
        HuffmanDecode(code, len, HC);
    }
    return 0;
}

void HuffmanTree(HuffTree HT, int length, HuffCode hc) {
    int i, min1, min2;
    HT[0].Weight = 999999;
    for (i = 1; i <= length; i++) {
        HT[i].Weight = hc[i].w;
        HT[i].LChild = HT[i].RChild = HT[i].Parent = -1;
    }
    for (; i < 2 * length; i++) {
        HT[i].LChild = HT[i].RChild = HT[i].Parent = -1;
    }
    for (i = length + 1; i < 2 * length; i++) {
        SelectHTNode(HT, i, &min1, &min2);
        HT[min1].Parent = i;
        HT[min2].Parent = i;
        HT[i].LChild = min1;
        HT[i].RChild = min2;
        HT[i].Weight = HT[min1].Weight + HT[min2].Weight;
    }
}

void SelectHTNode(HuffTree HT, int n, int *min1, int *min2) {
    int i;
    *min1 = *min2 = 0;
    for (i = 1; i < n; i++) {
        if (HT[i].Parent == -1) {
            if (HT[*min1].Weight >= HT[i].Weight) {
                *min2 = *min1;
                *min1 = i;
            } else if (HT[*min2].Weight > HT[i].Weight) {
                *min2 = i;
            }
        }
    }
}

void HuffmanCode(HuffTree HT, int len, HuffCode hc) {
    int i, j, tc, Stack[MaxSize], top = -1;
    char flag[MaxSize];
    HTNode th;
    for (i = 1; i <= len; i++) {
        top = -1;
        j = 0;
        th = HT[i];
        tc = i;
        while (th.Parent != -1) {
            Stack[++top] = th.Parent;
            if (HT[th.Parent].LChild == tc) {
                flag[top] = 'L';
                tc = th.Parent;
            }
            if (HT[th.Parent].RChild == tc) {
                flag[top] = 'R';
                tc = th.Parent;
            }
            th = HT[Stack[top]];
        }
        while (top != -1) {
            if (flag[top] == 'L') hc[i].code[j++] = '0';
            else hc[i].code[j++] = '1';
            Stack[top--];
        }
        hc[i].code[j] = '\0';
    }
}

void HuffmanDecode(char *code, int len, HuffCode hc) {
    for(int i = 1; i <= len; i ++) {
        if(strcmp(code, hc[i].code) == 0) {
            printf("%c\n", hc[i].c);
            return;
        }
    }
    printf("无此编码！\n");
}