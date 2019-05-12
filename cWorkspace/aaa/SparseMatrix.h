#ifndef _SPARSEMATRIX_H
#define _SPARSEMATRIX_H

#define OK 1
#define ERROR 0

typedef int Status;

typedef struct {
    int** SSMatrix;//ϡ�����ֵ�Ĵ��
    int i, j;//ϡ������������
}SMatrix;//ϡ�����

Status CreateSMatrix(SMatrix *);//����ϡ�����
Status DestroySMatrix(SMatrix *);//����ϡ�����
Status PrintSMatrix(SMatrix);//��ӡϡ�����
//Status TransposeSMatrix(SMatrix, SMatrix *);//ת��ϡ�����
#endif // !_SPARSEMATRIX_H




