from PIL import Image
import numpy as np
import matplotlib.pyplot as plt
import scipy as sp
import cv2


from skimage import io, data ,color

src = Image.open('flag.jpg')
Image._show(src)
im = src.split()

# img = cv2.imread('face.png')
# b = cv2.split(img)[0]
# g = cv2.split(img)[1]
# r = cv2.split(img)[2]
# hsv = color.convert_colorspace(img, 'RGB', 'HSV')

ar = np.array(im[0]).flatten()
plt.hist(ar,bins = 500,  facecolor = 'red', edgecolor = 'red')

ag = np.array(im[1]).flatten()
plt.hist(ag,bins = 500,  facecolor = 'green', edgecolor = 'green')

ab = np.array(im[2]).flatten()
plt.hist(ab,bins = 500,  facecolor = 'blue', edgecolor = 'blue')

plt.show()
