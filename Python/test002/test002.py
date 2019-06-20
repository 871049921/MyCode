# list = ["asd", 1]
#
# def func():
#     print('nmsl')
#     return 9
#
# dict = {}
# dict2 = {'x':1, 'y':2}
# dict2['z'] = 3
# i = 1
# # for i in dict2:
# #     print(i)
# #     if 9 > 0:
# #         for i in range(13):
# #             func()
#
# for i in range(3, 10):
#     func()
# print(dict2)

var = 123

def func():
    global var
    var = 111111

func()
print(var)

for i in range(1,10):
    print('hha ha ')