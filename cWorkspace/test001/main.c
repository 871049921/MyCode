#include <stdlib.h>
#include <process.h>
#include <stdio.h>

#define TRUE 1
#define FALSE 0
#define INVALID -1
#define NULL 0

#define total_instruction 320
#define total_vp 32
#define clear_period 50

typedef struct {
    int pn, pfn, counter, time;
} pl_type;
pl_type pl[32];
typedef struct pfc_struct {
    int pn, pfn;
    struct pfc_struct *next;
} pfc_type;
pfc_type pfc[32], *freepf_head, *busypf_head, *busypf_tail;

int diseffect, a[total_instruction];
int page[total_instruction], offset[total_instruction];

void initialize(int);

void FIFO(int);

void LRU(int);

void OPT(int);

void LFU(int);

void NUR(int);

int main() {
    int s, i, j;
    srand(10 * getpid());

    s = (float) 319 * rand() / 32767 / 32767 / 2 + 1;
    for (i = 0; i < total_instruction; i += 4) {
        if (s < 0 || s > 319) {
            printf("When i==%d,Error,s==%d", i, s);
            exit(0);
        }
        a[i] = s;
        a[i + 1] = a[i] + 1;
        a[i + 2] = (float) a[i] * rand() / 32767 / 32767 / 2;
        a[i + 3] = a[i + 2] + 1;
        s = (float) (318 - a[i + 2]) * rand() / 32767 / 32767 / 2 + a[i + 2] + 2;
        if ((a[i + 2] > 318) || (s > 319)) {
            printf("a[%d + 2],a number which is:%d and s==%d\n", i, a[i + 2], s);
        }
    }
    for (i = 0; i < total_instruction; i++) {
        page[i] = a[i] / 10;
        offset[i] = a[i] % 10;
    }
    for (i = 4; i <= 32; i++) {
        printf("%2d page frames", i);
        FIFO(i);
        LRU(i);
        OPT(i);
        LFU(i);
        NUR(i);
        printf("\n");
    }
}

void initialize(int total_pf) {
    int i;
    diseffect = 0;

    for (i = 0; i < total_vp; i++) {
        pl[i].pn = i;
        pl[i].pfn = INVALID;
        pl[i].counter = 0;
        pl[i].time = -1;
    }
    for (i = 0; i < total_pf - 1; i++) {
        pfc[i].next = &pfc[i + 1];
        pfc[i].pfn = i;
    }
    pfc[total_pf - 1].next = NULL;
    pfc[total_pf - 1].pfn = total_pf - 1;
    freepf_head = &pfc[0];
}

void FIFO(int total_pf) /*先进先出页面置换算法*/
{
    int i, j;
    pfc_type *p;
    initialize(total_pf);
    busypf_head = busypf_tail = NULL;
    for (i = 0; i < total_instruction; i++) {
        if (pl[page[i]].pfn == INVALID) /*页面失效*/
        {
            diseffect = diseffect + 1;
            if (freepf_head == NULL) /*无空闲页帧*/
            {
                p = busypf_head->next;
                pl[busypf_head->pn].pfn = INVALID; //将忙页帧队首页面作为换出页面
                freepf_head = busypf_head;
                freepf_head->next = NULL;
                busypf_head = p; //忙页帧头指针后移
            }
            p = freepf_head->next;   //有空闲页帧
            freepf_head->next = NULL;
            freepf_head->pn = page[i]; /* 将所需页面调入空闲页帧 */
            pl[page[i]].pfn = freepf_head->pfn;
            if (busypf_tail == NULL)  /* 若忙页帧队列为空，则将其头尾指针都指向刚调入页面所在的页帧 */
                busypf_head = busypf_tail = freepf_head;
            else { //否则，将刚调入页面所在的页帧挂在忙页帧队列尾部
                busypf_tail->next = freepf_head;
                busypf_tail = freepf_head;
            }
            freepf_head = p; //空闲页帧头指针后移
        }
    }
    printf("FIFO:%6.4f ", 1 - (float) diseffect / 320);
}

void LRU(int total_pf)         /*最近最久未使用页面置换算法*/
{
    int i, j;
    int min, minj, present_time;
    initialize(total_pf);
    present_time = 0;
    for (i = 0; i < total_instruction; i++) {
        if (pl[page[i]].pfn == INVALID)  /*页面失效*/
        {
            diseffect++;
            if (freepf_head == NULL)  /*无空闲页帧*/
            {
                min = 32767;
                for (j = 0; j < total_vp; j++) /*找出位于内存且time值最小的页面作为置换页面*/        {
                    if (min > pl[j].time && pl[j].pfn != INVALID) {
                        min = pl[j].time;
                        minj = j;
                    }
                }
                freepf_head = &pfc[pl[minj].pfn]; //腾出一个单元
                pl[minj].pfn = INVALID;
                pl[minj].time = -1;
                freepf_head->next = NULL;
            }
            pl[page[i]].pfn = freepf_head->pfn;  //有空闲页面,改为有效
            pl[page[i]].time = present_time;  //修改页面的访问时间
            freepf_head = freepf_head->next;   //减少一个free 页面
        } else
            pl[page[i]].time = present_time;  //命中则修改该单元的访问时间
        present_time++;
    }
    printf("LRU:%6.4f ", 1 - (float) diseffect / 320);
}

void NUR(int total_pf)    /* 最近未使用页面置换算法 */
{
    int i, j, dp, cont_flag, old_dp;
    initialize(total_pf);
    dp = 0;
    for (i = 0; i < total_instruction; i++) {
        if (pl[page[i]].pfn == INVALID) /*页面失效*/
        {
            diseffect++;
            if (freepf_head == NULL)   /*无空闲页帧*/
            {
                cont_flag = TRUE;
                old_dp = dp;
                while (cont_flag) {
                    if (pl[dp].counter == 0 && pl[dp].pfn != INVALID)
                        cont_flag = FALSE;  //找到位于内存且未被访问的页面
                    else {
                        dp++;
                        if (dp == total_vp) dp = 0; //将替换指针重新指向第一个页面
                        if (dp == old_dp) {/* 若内存中所有页面扫描完毕未找到访问位为0的页面，将内存中所有页面的访问位置0 */
                            for (j = 0; j < total_vp; j++)
                                pl[j].counter = 0;
                        }
                    }
                }
                freepf_head = &pfc[pl[dp].pfn];  //腾出一个单元
                pl[dp].pfn = INVALID;
                freepf_head->next = NULL;
            }
            pl[page[i]].pfn = freepf_head->pfn;  //有空闲页面,改为有效
            freepf_head = freepf_head->next;    //减少一个free 页面
        } else
            pl[page[i]].counter = 1;      //命中则将访问位置1
        if (i % clear_period == 0)  //清零周期到，将所有访问位清零
        {
            for (j = 0; j < total_vp; j++)
                pl[j].counter = 0;
        }
    }
    printf("NUR:%6.4f ", 1 - (float) diseffect / 320);
}

void OPT(int total_pf)  /* 最佳页面置换算法 */
{
    int i, j, max, maxpage, d, dist[total_vp];
    initialize(total_pf);
    for (i = 0; i < total_instruction; i++) {
        if (pl[page[i]].pfn == INVALID)   /*页面失效*/
        {
            diseffect++;
            if (freepf_head == NULL)   /*无空闲页面*/
            {
                for (j = 0; j < total_vp; j++) {
                    if (pl[j].pfn != INVALID)//所有位于内存页面的距离变量赋一足够大的数
                        dist[j] = 32767;
                    else //不在内存的页面该变量则置为0
                        dist[j] = 0;
                }
                d = 1;
                /* 对于位于内存且在当前访问页面之后将再次被访问的页面，dist重置为当				前页	面与之后首次出现该页面时两者之间的距离 */
                for (j = i + 1; j < total_instruction; j++) {
                    if (pl[page[j]].pfn != INVALID && dist[page[j]] == 32767)
                        dist[page[j]] = d;
                    d++;
                }
                max = -1;
                //查找dist变量值最大的页面作为换出页面
                for (j = 0; j < total_vp; j++) {
                    if (max < dist[j]) {
                        max = dist[j];
                        maxpage = j;
                    }
                }
                freepf_head = &pfc[pl[maxpage].pfn];   //腾出一个单元
                freepf_head->next = NULL;
                pl[maxpage].pfn = INVALID;
            }
            pl[page[i]].pfn = freepf_head->pfn;     //有空闲页面,改为有效
            freepf_head = freepf_head->next;     //减少一个free 页面
        }
    }
    printf("OPT:%6.4f ", 1 - (float) diseffect / 320);
}

void LFU(int total_pf)     /* 最少使用页面置换算法 */
{
    int i, j, min, minpage;
    initialize(total_pf);
    for (i = 0; i < total_instruction; i++) {
        if (pl[page[i]].pfn == INVALID)    //页面失效
        {
            diseffect++;
            if (freepf_head == NULL)       //无空闲页帧
            {
                min = 32767;
                for (j = 0; j < total_vp; j++) {  //查找位于内存且访问次数最少的页面作为换出页面
                    if (min > pl[j].counter && pl[j].pfn != INVALID) {
                        min = pl[j].counter;
                        minpage = j;
                    }
                    pl[j].counter = 0;
                }
                freepf_head = &pfc[pl[minpage].pfn];  //腾出一个单元
                pl[minpage].pfn = INVALID;
                freepf_head->next = NULL;
            }
            pl[page[i]].pfn = freepf_head->pfn;   //有空闲页面,改为有效
            pl[page[i]].counter++;     //增加页面访问次数
            freepf_head = freepf_head->next;   //减少一个free 页面
        } else
            pl[page[i]].counter++;  //命中增加页面访问次数
    }
    printf("LFU:%6.4f ", 1 - (float) diseffect / 320);
}

